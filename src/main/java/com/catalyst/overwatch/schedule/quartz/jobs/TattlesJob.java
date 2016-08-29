package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.exceptions.OverwatchScheduleException;
import com.catalyst.overwatch.schedule.model.*;
import com.catalyst.overwatch.schedule.model.external.SurveyResponse;
import com.catalyst.overwatch.schedule.repository.FlightRepository;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
import com.catalyst.overwatch.schedule.utilities.CustomNotificationParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.addAll;

/**
 * This job executes daily, finding schedules with respondents who have not submitted responses
 * for their surveys.  The job will send a "tattle" email that shows which surveys have not
 * reached %100 response rate, and which team members on that team have not filled out their
 * surveys.  Repeats every 12 hours.
 *
 * @author hmccardell
 * @author bfutral
 */
public class TattlesJob extends SchedulerBaseJob implements Job {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private OccurrenceRepository occurrenceRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private FlightRepository flightRepository;

  Logger logger = LogManager.getRootLogger();
  String responseUrl = NotificationConstants.SEARCH_SURVEY_RESPONSE_BY_DATE;

  /**
   * The main function of the TattlesJob, which executes the needed tasks.
   *
   * @param context A context bundle containing handles to various environment information, that
   *                is given to a quartz JobDetail instance as it is executed, and to a Trigger instance
   *                after the execution completes.
   * @throws JobExecutionException
   */
  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    logger.info("Tattles Job Begin... :");

    findAndUpdateOccurrences();

    List<Flight> flightListToProcess = new ArrayList<>();
    flightRepository.findByScheduleIsActiveAndIsClosed(true, false)
            .stream()
            .forEach(flight -> {
              calculateThresholdForFlight(flight);
            });

    logger.info("Tattles Job End... :");

  }

  /**
   * Calculates whether or not the response threshold has been met for a flight of occurrences.
   *
   * @param flight a flight of occurrences to calculate the threshold for.
   */
  public void calculateThresholdForFlight(Flight flight) {
    logger.info(flight);

    long thresholdMark = 0;
    List<Occurrence> occurrenceList = new ArrayList<>();

    int completeCounter = 0;

    occurrenceList.addAll(occurrenceRepository.findByScheduleIdAndFlightNumber(flight.getScheduleId(), flight.getFlightNumber()));

    List<Occurrence> tattleOnList = new ArrayList<>();
    //Loop through each occurrence in this flight to see if it has met the threshold
    for (Occurrence occurrence : occurrenceList) {
      ++thresholdMark;
      logger.info("flight number; " + occurrence.getFlightNumber());
      logger.info("generation date: " + occurrence.getGenerationDate());
      logger.info(occurrence.toString());
      if (occurrence.getIsComplete() == true) {
        ++completeCounter;
        logger.info(occurrence.getRespondent().getUser().getEmail() + " has responded to the survey");

      } else {
        logger.info(occurrence.getRespondent().getUser().getEmail() + " did not respond to the survey");
        tattleOnList.add(occurrence);
        logger.info("THIS IS THE SENDLIST: " + tattleOnList);
      }
    }

    //Threshold met, generate reports and stakeholder notification
    if (completeCounter == thresholdMark) {
      logger.info("Threshold met");
      logger.info("Updating the flight table");
      flight.setIsClosed(true);
      flightRepository.save(flight);
      // TODO: 8/11/2016 send "threshold met" notification to stakeholders
    }

    //Threshold not met, generate tattles for the delinquent respondents
    else {
      logger.info("Threshold has not been met");
      logger.info("Respondents in flight:  " + thresholdMark);
      logger.info("Number of responses: " + completeCounter);
      tattleConstructor(tattleOnList);
    }
  }

  public void tattleConstructor(final List<Occurrence> occurrences) {
    Set<Respondent> sendTattleList = new HashSet<>();
    for (Occurrence occurrence : occurrences) {
      Schedule schedule = scheduleRepository.findByRespondentsId(occurrence.getRespondent().getId());
      sendTattleList.addAll(determineTattleRecipients(schedule));
    }

    String emailAddress = null;
    logger.info("SEND TATTLE TO: " + sendTattleList);
    for(Respondent respondent : sendTattleList) {
      emailAddress = respondent.getUser().getEmail();
      logger.info("EMAIL: " + emailAddress);
    }

    String tattle = buildTattleBody(occurrences);

    generateNotification(emailAddress,
            tattle,
            NotificationConstants.TATTLE_SUBJECT,
            "Tattle Job");

    logger.info("GENERATED!");
  }

  /**
  * Builds body for the tattle.
  * */
  public String buildTattleBody(List<Occurrence> occurrences) {

    StringBuilder usersString = new StringBuilder();

    for (Occurrence occurrence : occurrences) {
      Respondent respondent = occurrence.getRespondent();
      String firstName = respondent.getUser().getFirstName();
      String lastName = respondent.getUser().getLastName();
      usersString.append(firstName + " " + lastName + "\n");
    }

    StringBuilder tattle = new StringBuilder();
    Long scheduleId = occurrences.get(0).getScheduleId();
    String surveyName;

    Schedule schedule = scheduleRepository.findById(scheduleId);
    surveyName = schedule.getTemplateName() + ": ";

    tattle.append(NotificationConstants.TATTLE_BODY_BEGIN).append(" ").append(surveyName).append("\n\n")
            .append(usersString.toString()).append("\n").append(NotificationConstants.TATTLE_BODY_END);
    logger.info("TATTLE: " + tattle);
    return tattle.toString();
  }

  /**
   * Finds all occurrences with response data and marks them complete by updating the
   * occurrence in the Schedule database.
   */
  public void findAndUpdateOccurrences() {

    getOccurrenceResponses()
            .stream()
            .forEach(o -> {
              Occurrence occurrenceToUpdate = new Occurrence();
              long occurrenceId = Long.parseLong(o.getOriginatorId());
              logger.info("occurrenceId: " + occurrenceId);
              occurrenceToUpdate = occurrenceRepository.findById(occurrenceId);
              logger.info("occurrence: " + occurrenceToUpdate.toString());
              occurrenceToUpdate.setIsComplete(true);
              occurrenceRepository.save(occurrenceToUpdate);
            });

  }

  /**
   * Contacts the SurveyResponse service to find all occurrences that had answer submissions yesterday.
   *
   * @return a list of SurveyResponses gotten from the SurveyReponse service.
   */
  private List<SurveyResponse> getOccurrenceResponses() {

    List<SurveyResponse> extractedResponseData = new ArrayList<>();

    try {
      Resources<SurveyResponse> surveyResponses = restTemplate.exchange(
              responseUrl + LocalDate.now().minus(1, ChronoUnit.DAYS),
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<Resources<SurveyResponse>>() {
              }).getBody();

      extractedResponseData.addAll(extractResponseData(surveyResponses));

    } catch (Exception e) {
      logger.error("Error occurred while contacting Survey Response service: ", e);

      throw new OverwatchScheduleException("Error occurred while contacting Survey Response service: ", e);

    }

    return extractedResponseData;
  }

  /**
   * Takes in a Resources object and returns the ResponseData content data,
   * which as a Collection and returns that collection as an array list.
   *
   * @param responseData Resources object to extract the ResponseData from.
   * @return List of ResponseData objects extracted from the Resources object.
   */
  private List<SurveyResponse> extractResponseData(final Resources<SurveyResponse> responseData) {
    List<SurveyResponse> extractedResponseData = new ArrayList<>();
    extractedResponseData.addAll(responseData.getContent());
    return extractedResponseData;
  }

  /**
  * Determines which respondent(s) will have tattles sent to them.
  * Checks for respondents with a ROLE of Engagement Manager and/or Tech Lead AttributeValue,
  * and adds them to a list.
  *
  * @param schedule
  * @return List of respondents to receive tattles.
  * */
  private List<Respondent> determineTattleRecipients(Schedule schedule){
    List<Respondent> tattleToList = new ArrayList<>();
    Set<Respondent> checkList = new HashSet<>();
    checkList.addAll(schedule.getRespondents());
    logger.info("checkList: " + checkList);

    if(!checkList.isEmpty()) {
      for (Respondent respondent : checkList){
        for(AllowedAttribute allowedAttribute : respondent.getAllowedAttributes()){
          if(allowedAttribute.getAttributeValue().equals("Engagement Manager") || allowedAttribute.getAttributeValue().equals("Tech Lead")
                  && allowedAttribute.getAttributeType().getName().equals("ROLE")){
            logger.info("Sending Tattles to the following: " + respondent);
            tattleToList.add(respondent);
          }
        }
      }
    }
    return tattleToList;
  }

}

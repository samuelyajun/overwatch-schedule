package com.catalyst.overwatch.schedule.quartz.jobs;

import static com.google.common.base.Preconditions.checkNotNull;
import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.constants.Urls;
import com.catalyst.overwatch.schedule.exceptions.OverwatchScheduleException;
import com.catalyst.overwatch.schedule.model.*;
import com.catalyst.overwatch.schedule.model.external.SurveyResponse;
import com.catalyst.overwatch.schedule.repository.FlightRepository;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
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
import java.util.*;


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

  @Autowired
  private Urls urls;

  Logger logger = LogManager.getRootLogger();

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

        long thresholdMark = 0;
        long id = 0;
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
            }
        }
        logger.info("THIS IS THE SENDLIST: " + tattleOnList);

        //Threshold met, generate reports and stakeholder notification
        if (completeCounter == thresholdMark) {
            logger.info("Threshold met");
            logger.info("Updating the flight table");
            flight.setIsClosed(true);
            flightRepository.save(flight);
            logger.info("Retrieve a schedule using id: " + id);
            Schedule scheduleById = scheduleRepository.findById(id);
            logger.info("Report endpoint url: " + urls.getReportEndpoint());
            logger.info("Schedule retrieved: " + scheduleById.toString());
            logger.info(restTemplate.getForObject(urls.getReportEndpoint() + 
                    scheduleById.getTemplateUri(), Object.class).toString());

        //Threshold not met, generate tattles for the delinquent respondents
        } else {
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

        logger.info("SEND TATTLE TO: " + sendTattleList);
        for (Respondent respondent : sendTattleList) {
            String emailAddress = null;
            if(respondent.getUser().getEmail() != null) {
                emailAddress = respondent.getUser().getEmail();
                logger.info("EMAIL: " + emailAddress);

            generateNotification(emailAddress,
                    buildTattleBody(occurrences),
                    NotificationConstants.TATTLE_SUBJECT,
                    "Tattle Job");

            logger.info("GENERATED!");
        }
        else{
            logger.info("Respondent email is null.");
        }
    }

  }

  /**
  * Builds the body of the Tattle email that is sent when a respondent in a given occurrence
  * has not completed the survey.
  *
  * @return a string containing a pre-determined message and a list with the names of respondents to be "tattled" on.
  * */
  public String buildTattleBody(List<Occurrence> occurrences) {

    checkNotNull(occurrences, "Occurrences cannot be empty");

    StringBuilder usersString = new StringBuilder();
    for (Occurrence occurrence : occurrences) {
      if(occurrence.getRespondent() != null && occurrence.getRespondent().getUser() != null &&
              occurrence.getRespondent().getUser().getFirstName() != null && occurrence.getRespondent().getUser().getLastName() != null &&
              occurrence.getIsComplete() == false) {

                usersString.append(occurrence.getRespondent().getUser().getFirstName() + " " +
                occurrence.getRespondent().getUser().getLastName() + "\n");
      } else {
        logger.error("Respondents are null");
      }
    }

    String surveyName = null;
    Long scheduleId = occurrences.get(0).getScheduleId();
    Schedule schedule = scheduleRepository.findById(scheduleId);

    if(schedule != null) {
      surveyName = schedule.getTemplateName();
    } else {
      logger.error("Schedule is null");
    }

    return new StringBuilder(NotificationConstants.TATTLE_BODY_BEGIN).append(" ").append(surveyName).append(": ").append("\n\n")
            .append(usersString.toString()).append("\n").append(NotificationConstants.TATTLE_BODY_END).toString();
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
              urls.getSearchSurveyResponseByDate() + LocalDate.now().minus(1, ChronoUnit.DAYS),
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
  public List<Respondent> determineTattleRecipients(Schedule schedule){
      checkNotNull(schedule, "Schedule cannot be null.");
      checkNotNull(schedule.getRespondents(), "Respondents must exist");
    List<Respondent> tattleToList = new ArrayList<>();
    Set<Respondent> checkList = new HashSet<>();
    checkList.addAll(schedule.getRespondents());
    logger.info("checkList: " + checkList);

    if(!checkList.isEmpty()) {
      for (Respondent respondent : checkList){
        for(AllowedAttribute allowedAttribute : respondent.getAllowedAttributes()){
          if(allowedAttribute.getAttributeValue().equals("Tech Lead") || allowedAttribute.getAttributeValue().equals("Engagement Manager")
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

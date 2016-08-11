package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.model.CheckOccurrence;
import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.Respondent;
import com.catalyst.overwatch.schedule.model.Schedule;
import com.catalyst.overwatch.schedule.model.external.SurveyResponse;
import com.catalyst.overwatch.schedule.repository.CheckOccurrenceRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This job executes daily, finding schedules with respondents who have not submitted responses
 * for their surveys.  The job will send a "tattle" email that shows which surveys have not
 * reached %100 response rate, and which team members on that team have not filled out their
 * surveys.  Repeats every 12 hours.
 *
 * @author hmccardell
 */
public class TattlesJob extends SchedulerBaseJob implements Job {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private OccurrenceRepository occurrenceRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private CheckOccurrenceRepository checkOccurrenceRepository;

  Logger logger = LogManager.getRootLogger();
  String responseUrl = NotificationConstants.SEARCH_SURVEY_RESPONSE_BY_DATE;
  List<Occurrence> occurrencesList = new ArrayList<>();
  LocalDate yesterdaysDate = LocalDate.now().minus(1, ChronoUnit.DAYS);

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

    logger.info("Tattles Job Executing... :");
    findAndUpdateOccurrences();

    List<Schedule> activeSchedules = new ArrayList<>();
    activeSchedules.addAll(scheduleRepository.findByIsActive(true));

    //Loop through all active schedules and calculate the threshold for each
    for (Schedule schedule : activeSchedules) {
      calculateThreshold(schedule);
    }

  }

  /**
   * Calculates the threshold for a given schedule.
   *
   */
  public void calculateThreshold(final Schedule schedule) {
    int numberOfRespondents = schedule.getRespondents().size();

    List<Occurrence> sendList = new ArrayList<>();
    List<CheckOccurrence> checkOccurrenceList = new ArrayList<>();
    List<Occurrence> occurrenceList = new ArrayList<>();
    checkOccurrenceList.addAll(checkOccurrenceRepository.findByScheduleIdAndIsClosed(schedule.getId(), false));

    //Loop through each CheckOccurrence that is not closed
    for(CheckOccurrence checkOccurrence : checkOccurrenceList){

      int completeCounter = 0;
      int notCompleteCounter = 0;

      occurrenceList.addAll(occurrenceRepository.findByScheduleIdAndOccurrenceNumber(schedule.getId(), checkOccurrence.getOccurrenceNumber()));

      //Loop through each occurrence in that flight of occurrences to see if it is completed or not
      for(Occurrence occurrence : occurrenceList){
        if(occurrence.isComplete() == true){
          ++completeCounter;
        }
        else{
          ++notCompleteCounter;
          sendList.add(occurrence);
        }
      }

      if(completeCounter != numberOfRespondents){
        logger.info("Threshold has not been met");
        // TODO: 8/11/2016 send tattles
      }
      if(completeCounter == numberOfRespondents && notCompleteCounter == 0){
        logger.info("Threshold met");
        // TODO: 8/11/2016 send "threshold met" notification to stakeholders

      }

    }



  }

  /**
   * Finds all occurrences with response data and marks them complete by updating the
   * occurrence in the Schedule database.
   */
  public void findAndUpdateOccurrences() {

    getOccurrenceResponses(yesterdaysDate)
            .stream()
            .forEach(o -> {
              Occurrence occurrenceToUpdate = new Occurrence();
              long occurrenceId = Long.parseLong(o.getOriginatorId());
              logger.info("occurrenceId: " + occurrenceId);
              occurrenceToUpdate = occurrenceRepository.findById(occurrenceId);
              logger.info("occurrence: " + occurrenceToUpdate.toString());
              occurrenceToUpdate.setComplete(true);
              occurrenceRepository.save(occurrenceToUpdate);
            });

  }

  /**
   * Contacts the SurveyResponse service to find all occurrences that had answer submissions yesterday.
   *
   * @param yesterdaysDate yesterday's date
   * @return a list of SurveyResponses gotten from the SurveyReponse service.
   */
  private List<SurveyResponse> getOccurrenceResponses(final LocalDate yesterdaysDate) {

    List<SurveyResponse> extractedResponseData = null;

    try {
      Resources<SurveyResponse> surveyResponses = restTemplate.exchange(
              responseUrl + yesterdaysDate,
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<Resources<SurveyResponse>>() {
              }).getBody();

      extractedResponseData = extractResponseData(surveyResponses);

    } catch (Exception e) {
      logger.error("Error occurred while contacting Survey Response service: ", e);
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
    List<SurveyResponse> extractedResponseData;

    extractedResponseData = new ArrayList<>(responseData.getContent());

    return extractedResponseData;
  }

}

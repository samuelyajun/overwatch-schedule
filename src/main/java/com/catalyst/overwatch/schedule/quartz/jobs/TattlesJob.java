package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.exceptions.OverwatchScheduleException;
import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.external.SurveyResponse;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.sun.xml.internal.bind.v2.TODO;
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

  Logger logger = LogManager.getRootLogger();
  String responseUrl = NotificationConstants.SEARCH_SURVEY_RESPONSE_BY_DATE;
  List<Occurrence> occurrencesList = new ArrayList<>();

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
    logger.info("Tattles Job End... :");


    //TODO more tasks for this job will be created. Updating occurrences is only the first step.

  }

  /**
   * Finds all occurrences with response data and marks them complete by updating the
   * occurrence in the Schedule database.
   */
  public void findAndUpdateOccurrences(){

    getOccurrenceResponses()
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
   * @return a list of SurveyResponses gotten from the SurveyReponse service.
   */
  private List<SurveyResponse> getOccurrenceResponses(){

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

}

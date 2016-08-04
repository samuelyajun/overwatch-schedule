package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.external.SurveyResponse;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
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
import java.time.temporal.ChronoField;
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
  String responseUrl = NotificationConstants.RESPONSE_BASE_URL + NotificationConstants.SURVEY_RESPONSE;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    logger.info("Tattles Job Executing... :");
    updateOccurrences();

  }

  public void updateOccurrences() {

    List<Occurrence> occurrencesList = new ArrayList<>();
    LocalDate yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS);
    logger.info(LocalDate.now() + " is today " + "yesterday: " + yesterday);

//    getOccurrenceResponses()
//    .stream()
//    .forEach(o -> {
//
//      logger.info(o.getId());
//      long id = o.getId();
//
//    });

  }

  /**
   * Takes in a Resources object and returns the ResponseData content data,
   * which as a Collection and returns that collection as an array list.
   *
   * @param responseData Resources object to extract the ResponseData from.
   * @return List of ResponseData objects extracted from the Resources object.
   */
  private List<SurveyResponse> extractResponseData(Resources<SurveyResponse> responseData) {
    List<SurveyResponse> extractedResponseData;

    extractedResponseData = new ArrayList<>(responseData.getContent());

    return extractedResponseData;
  }

  private List<SurveyResponse> getOccurrenceResponses() {

    Resources<SurveyResponse> surveyResponses = restTemplate.exchange(
            responseUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Resources<SurveyResponse>>() {
            }).getBody();

    List<SurveyResponse> extractedResponseData = extractResponseData(surveyResponses);

    return extractedResponseData;
  }

}

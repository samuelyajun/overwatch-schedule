package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.model.external.SurveyResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

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

  Logger logger = LogManager.getRootLogger();
  RestTemplate restTemplate = new RestTemplate();
  String responseUrl = NotificationConstants.RESPONSE_BASE_URL + NotificationConstants.SURVEY_RESPONSE;


  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    logger.info("Tattles Job Executing... :");
    updateOccurrences();




  }

  public void updateOccurrences(){
    String getAllUrl = responseUrl;
    responseUrl = responseUrl + "/1";



    SurveyResponse surveyResponse = restTemplate.getForObject(responseUrl, SurveyResponse.class);

    logger.info(surveyResponse.toString());
    logger.info(responseUrl);


    Resources<SurveyResponse> surveyResponses = restTemplate.exchange(
            getAllUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Resources<SurveyResponse>>() {}).getBody();

    logger.info(getAllUrl);
    List<SurveyResponse> extractedResponseData = extractResponseData(surveyResponses);
    logger.info(extractedResponseData);

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
}

package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.constants.Urls;
import com.catalyst.overwatch.schedule.exceptions.OverwatchScheduleException;
import com.catalyst.overwatch.schedule.model.Notification;
import com.catalyst.overwatch.schedule.model.external.SurveyLink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import static com.google.common.base.Preconditions.*;

/**
 * A base class for Overwatch Schedule jobs, providing basic utilities common to multiple jobs.
 *
 * @author hmccardell
 */
public abstract class SchedulerBaseJob {

  Logger logger = LogManager.getLogger((SchedulerBaseJob.class));
  
  @Autowired
  RestTemplate restTemplate;

  /**
   * Utilizes a surveySuid and an occurrence id to build a functional http link to a survey.
   *
   * @param surveySuid   the id of the survey to build into the link
   * @param originatorId the occurrence id to include in the link
   * @return a valid link to a survey for a specific user's occurrence
   */
  protected String buildSurveyLink(final String surveySuid, final long originatorId) {

    StringBuilder completedLink = new StringBuilder();
    String originator = NotificationConstants.SURVEYS_ORIGINATOR_PARAM;

    completedLink.append(Urls.getInstance().getFrontEndBaseUrl());
    completedLink.append(surveySuid + "&?" + originator + originatorId);

    logger.info("build survey link: " + completedLink.toString());

    return completedLink.toString();
  }


  /**
   * Makes a restful call to the notifications service to generate an email to a user. This email will include
   * a clickable hyperlink in the body that will take them to a survey they can fill out and submit.
   *
   * @param emailAddress   the recipient's email address.
   * @param body           the body of the email to send.
   * @param subject        the subject of the email to send.
   * @param errorReference the name of the calling job for error reference.
   */
  public void generateNotification(final String emailAddress, final String body, final String subject,
                                   final String errorReference) {

    checkNotNull(emailAddress, "emailAddress cannot be null");
    checkNotNull(body, "body cannot be null");
    checkNotNull(subject, "subject cannot be null");
    checkNotNull(errorReference, "errorReference cannot be null");
    
    String[] recipientAddress = new String[]{emailAddress};
    Notification notification = new Notification(recipientAddress, subject, body);

    try {
      restTemplate.postForEntity(Urls.getInstance().getNotificationEndpoint(), notification, Notification.class);
      logger.info("Generated email from rest template");
    } catch (Exception e) {
      logger.error("Quartz " + errorReference + " Error:  exception occurred while calling Notification service", e);
      throw new OverwatchScheduleException("Error:  exception occurred while calling Notification service", e);
    }

  }

  /**
   * Utilizes a surveySuid and an occurrence id to build a functional http link to a survey.
   *
   * @param templateLink the relative link of a template on the survey service.
   * @param surveyName the name of the survey from the survey service.
   *
   * @return a valid link to a survey for a specific user's occurrence.
   */
  public String newBuildSurveyLink(final String templateLink, final String surveyName){

    String surveyUrlToPost = NotificationConstants.SURVEY_ENDPOINT;

    StringBuilder completedLink = new StringBuilder();
    completedLink.append(NotificationConstants.FRONT_END_BASE_URL);
    SurveyLink surveyLink = new SurveyLink(templateLink, surveyName);

    logger.info("template: " + templateLink);
    logger.info("name: " + surveyName);

    try {
      SurveyLink returnedSurveyLink = restTemplate.postForEntity(surveyUrlToPost, surveyLink, SurveyLink.class).getBody();
      logger.info("post response: " + returnedSurveyLink);
      completedLink.append(returnedSurveyLink.getSurveyDisplayLink());
      logger.info("Scheduler Base Job => Survey Display Link: " + completedLink.toString());
    }catch (Exception e){
      logger.error("Quartz Job buildCompletedLink, exception occurred while contacting the Survey Service", e);
    }

    return completedLink.toString();
  }

  /**
   * Appends an originatorId (occurrence id) to a surveylink.
   *
   * @param link the link to add an originator to.
   * @param originatorId the originatorId to add to the link.
   *
   * @return a link with an originatorId so that a user can click on it and have their answers recorded.
   */
  public String addOriginatorIdToLink(StringBuilder link, final long originatorId){
    String originator = NotificationConstants.SURVEYS_ORIGINATOR_PARAM;
    link.append("&?" + originator + originatorId);

    return link.toString();
  }
  
}

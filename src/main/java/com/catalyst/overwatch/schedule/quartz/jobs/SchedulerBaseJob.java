package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.model.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

/**
 * A base class for Overwatch Schedule jobs, providing basic utilities common to multiple jobs.
 *
 * @author hmccardell
 */
public abstract class SchedulerBaseJob {

  Logger logger = LogManager.getLogger((SchedulerBaseJob.class));
  RestTemplate restTemplate = new RestTemplate();

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

    completedLink.append(NotificationConstants.FRONT_END_BASE_URL);
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

    String[] recipientAddress = new String[]{emailAddress};
    Notification notification = new Notification(recipientAddress, subject, body);

    try {
      restTemplate.postForEntity(NotificationConstants.NOTIFICATION_ENDPOINT, notification, Notification.class);
      logger.info("Generated email from rest template");
    } catch (Exception e) {
      logger.error("Quartz " + errorReference + " Error:  exception occurred while calling Notification service", e);
    }

  }

}

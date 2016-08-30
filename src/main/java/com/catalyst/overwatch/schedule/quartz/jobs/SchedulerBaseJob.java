package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.constants.Urls;
import com.catalyst.overwatch.schedule.exceptions.OverwatchScheduleException;
import com.catalyst.overwatch.schedule.model.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import static com.google.common.base.Preconditions.*;

/**
 * A base class for Overwatch Schedule jobs, providing basic utilities common to multiple jobs.
 *
 * @author hmccardell
 * @author bfutral
 */
public abstract class SchedulerBaseJob {

  @Autowired
  private Urls urls;

  @Autowired
  private RestTemplate restTemplate;

  Logger logger = LogManager.getLogger((SchedulerBaseJob.class));

  /**
   * Utilizes a surveySuid and an occurrence id (aka originatorId) to build a functional http link to a survey.
   *
   * @param surveySuid   the id of the survey to build into the link
   * @param originatorId the occurrence id to include in the link
   * @return a valid link to a survey for a specific user's occurrence
   */
  protected String buildSurveyLink(final String surveySuid, final Long originatorId) {
    
    checkNotNull(surveySuid, "surveySuid cannot be null");
    checkNotNull(originatorId, "originatorId cannot be null");

    StringBuilder completedLink = new StringBuilder();
    String originator = NotificationConstants.SURVEYS_ORIGINATOR_PARAM;

    completedLink.append(urls.getSurveyURLEndpoint());
    
    logger.info("Base email URL: " + completedLink.toString()); 
    logger.info("surveySuid: " + surveySuid);
    logger.info("occurrenceId: " + originatorId);
    
    completedLink.append("?suid=" + surveySuid);
    completedLink.append("&" + originator + originatorId);

    logger.info("Scheduler Base Job => Survey Display Link: " + completedLink.toString());

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
    
    logger.info("Sending email to: " + emailAddress);
    
    String[] recipientAddress = new String[]{emailAddress};
    Notification notification = new Notification(recipientAddress, subject, body);

    try {
      restTemplate.postForEntity(urls.getNotificationEndpoint(), notification, Notification.class);
      logger.info("Generated email from rest template");
      logger.info("restTemplate: " + restTemplate);
    } catch (Exception e) {
      logger.error("Quartz " + errorReference + " Error:  exception occurred while calling Notification service", e);
      throw new OverwatchScheduleException("Error:  exception occurred while calling Notification service", e);
    }

  }

}

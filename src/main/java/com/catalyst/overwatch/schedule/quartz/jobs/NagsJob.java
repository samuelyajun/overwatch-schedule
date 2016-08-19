package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.Schedule;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
import com.catalyst.overwatch.schedule.utilities.CustomNotificationParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * This is the daily reminder job for scheduled surveys.  All occurrences that have not been completed
 * and have the current date as the generation date are pulled from the database.  Nag notifications
 * are sent to each respondent with the link to their survey.
 *
 * @author hmccardell
 */
public class NagsJob extends SchedulerBaseJob implements Job {

  @Autowired
  private OccurrenceRepository occurrenceRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  Logger logger = LogManager.getRootLogger();

  /**
   * The main function of the NagsJob, which executes the needed tasks.
   *
   * @param context A context bundle containing handles to various environment information, that
   *                is given to a quartz JobDetail instance as it is executed, and to a Trigger instance
   *                after the execution completes.
   * @throws JobExecutionException
   */
  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    logger.info("Nags Job Executing... :");

    LocalDate todaysDate = LocalDate.now();

    occurrenceRepository.findByGenerationDateLessThanEqualAndIsComplete(todaysDate, false)
            .stream()
            .forEach(s -> {
              nagConstructor(s);
            });
    
    logger.info("Nags Job Complete");
  }

  /**
   * This method uses the occurrence's respondent to find the schedule it is related to.
   * The schedule's template link and name are used to construct a nag notification specific
   * to the occurrence respondent.
   *
   * @param occurrence
   */
  void nagConstructor(final Occurrence occurrence) {

    Schedule schedule = scheduleRepository.findByRespondentsId(occurrence.getRespondent().getId());
    String templateName = schedule.getTemplateName();
    String templateSuid = schedule.getTemplateUri();
    String emailAddress = occurrence.getRespondent().getUser().getEmail();
    String completeSurveyLink = buildSurveyLink(templateSuid, occurrence.getId());

    String body = buildNagBody(completeSurveyLink);

    generateNotification(emailAddress,
            CustomNotificationParser.notificationBodyParser(templateName) + body,
            CustomNotificationParser.notificationSubjectParser(templateName),
            "Nags Job");
  }

  /**
   * Uses StringBuilder to construct the body of the nag notification.
   *
   * @param completeSurveyLink the complete functioning link to be included in the nag.
   * @return
   */
  String buildNagBody(String completeSurveyLink) {

    StringBuilder body = new StringBuilder();
    body.append(NotificationConstants.NAG_BODY + "\n\n");
    body.append("Survey Link: " + completeSurveyLink);

    return body.toString();
  }

  /**
   * Uses StringBuilder to construct the subject of the nag notification.
   *
   * @param templateName the name of the template to be included in the nag's subject line.
   * @return
   */
  String buildNagSubject(String templateName) {

    StringBuilder subject = new StringBuilder();
    subject.append("Please complete " + templateName + " by EOD");

    return subject.toString();
  }

}

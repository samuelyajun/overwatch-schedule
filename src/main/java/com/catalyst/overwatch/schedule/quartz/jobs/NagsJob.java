package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.Schedule;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * Created by hmccardell on 7/28/2016.
 */
public class NagsJob extends SchedulerBaseJob implements Job {

  @Autowired
  private OccurrenceRepository occurrenceRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  Logger logger = LogManager.getLogger(NagsJob.class);

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    logger.info("Nags Job Executing... :");

    LocalDate todaysDate = LocalDate.now();

    occurrenceRepository.findByGenerationDateAndIsComplete(todaysDate, false)
            .stream()
            .forEach(s -> {
              generateNagNotification(s);
            });
  }

  void generateNagNotification(Occurrence occurrence) {

    Schedule schedule = scheduleRepository.findByRespondentsId(occurrence.getRespondent().getId());
    String templateName = schedule.getTemplateName();
    String templateSuid = schedule.getTemplateUri();
    String emailAddress = occurrence.getRespondent().getUser().getEmail();
    String completeSurveyLink = buildSurveyLink(templateSuid, occurrence.getId());

    String subject = "Please complete " + templateName + " by EOD";
    StringBuilder body = new StringBuilder();
    body.append(NotificationConstants.NAG_BODY + "\n\n");
    body.append("Survey Link: " + completeSurveyLink);
    generateNotification(emailAddress, body.toString(), subject, "Nags Job");
  }
}

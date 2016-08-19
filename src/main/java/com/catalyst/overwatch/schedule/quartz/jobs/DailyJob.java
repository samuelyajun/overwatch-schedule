package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.model.Flight;
import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.Respondent;
import com.catalyst.overwatch.schedule.model.Schedule;
import com.catalyst.overwatch.schedule.repository.FlightRepository;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This daily job will run every day on a schedule designated in the SchedulerConfig.  In the alpha build,
 * schedules are checked to see if the current date falls on their frequency. If it does, occurrences will
 * be generated for each respondent on each schedule.  These occurrences are posted to the database, and
 * their ids are used to construct hyperlinks which are then packaged into a restful call to the notification
 * service, which generates an email to the respondent with their survey link.
 *
 * @author hmccardell
 */
public class DailyJob extends SchedulerBaseJob implements Job {

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private OccurrenceRepository occurrenceRepository;

  @Autowired
  private FlightRepository flightRepository;

  Logger logger = LogManager.getRootLogger();

  /**
   * The main function of the DailyJob, which executes the needed tasks.
   *
   * @param context A context bundle containing handles to various environment information, that
   *                is given to a quartz JobDetail instance as it is executed, and to a Trigger instance
   *                after the execution completes.
   * @throws JobExecutionException
   */
  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    logger.info("Daily Job Start");
    List<Schedule> cleanedSchedules = new ArrayList<>();
    cleanedSchedules.addAll(getSchedulesFromRepositoryAndProcess());
    logger.info("cleaned schedules" + cleanedSchedules);
    generateOccurrencesForToday(cleanedSchedules);
    logger.info("Daily Job End");

  }

  /**
   * Takes a list of schedules and iterates over them.  For each respondent in each schedule, an
   * occurrence is generated and saved to the database.  The id is captured and used to build an
   * http link, which is sent to the respondent by a restful call to the notification service.
   *
   * @param scheduleList a list of schedules that are ready for occurrence generation.
   */
  protected void generateOccurrencesForToday(List<Schedule> scheduleList) {
    
    for (Schedule schedule : scheduleList) {
      long flightNumber = 1;
      String templateName = schedule.getTemplateName();

      List<Flight> flightList = new ArrayList<>();
      flightList.addAll(flightRepository.findByScheduleId(schedule.getId()));

      //if flights exist, the new flight number is one greater than the previous max for that schedule
      if (!flightList.isEmpty()) {
        flightNumber = flightRepository.findLargestByScheduleId(schedule.getId());
        logger.info("current flightNumber: " + flightNumber);
        ++flightNumber;
      }

      logger.info("new flight number: " + flightNumber);
      
      for (Respondent respondent : schedule.getRespondents()) {
    	  
	    StringBuilder body = new StringBuilder();
	    
        Occurrence occurrenceToPost = new Occurrence(respondent, schedule.getId(), flightNumber);
        Occurrence postedOccurrence = occurrenceRepository.save(occurrenceToPost);
        
	    StringBuilder surveyLinkForThisRespondent = new StringBuilder();
	    surveyLinkForThisRespondent.append(buildSurveyLink(schedule.getTemplateUri(), postedOccurrence.getId()));
	    
        body.append("Link to survey: " + surveyLinkForThisRespondent);

        generateNotification(respondent.getUser().getEmail(),
                CustomNotificationParser.notificationBodyParser(templateName) + body,
                CustomNotificationParser.notificationSubjectParser(templateName),
                "Daily Job");

        logger.info("Generate notification: " + respondent.getUser().getEmail() + "    link: " + surveyLinkForThisRespondent);
        logger.info(respondent.getUser());
      }
      Flight flightToPersist = new Flight(schedule.getId(), true, flightNumber, false);
      flightRepository.save(flightToPersist);
    }
  }

  /**
   * Determines the number of days between the start date of a schedule and today's date.
   * Uses the value of the schedule's frequency and the days between to decide if today's date falls on that frequency.
   * If the schedule's start date is today and the frequency is one time, then today is on the frequency.
   *
   * @param schedule the schedule to check.
   * @return boolean true when today's date falls on the schedule's frequency, else false.
   */
  protected boolean isTodayOnScheduleFrequency(Schedule schedule) {

    LocalDate todaysDate = LocalDate.now();
    boolean isOnFrequency = false;

    LocalDate thisScheduleStartDate = schedule.getStartDate();
    int weeksValueOfFrequency = schedule.getFrequency().getValue();
    long daysBetween = ChronoUnit.DAYS.between(thisScheduleStartDate, todaysDate);

    if (schedule.getFrequency().getValue() == 0 && todaysDate.equals(schedule.getStartDate().plus(1, ChronoUnit.DAYS))) {
      isOnFrequency = true;
      logger.info("Schedule frequency is ONE_TIME, start date was yesterday, should create occurrence.");
    } else if (schedule.getFrequency().getValue() != 0 && daysBetween % weeksValueOfFrequency == 0) {
      isOnFrequency = true;
      logger.info("Today's date lands on the schedule frequency, should create occurrence." + schedule.getStartDate() + " " + schedule.getFrequency());
    }

    return isOnFrequency;
  }

  /**
   * Retrieves schedules from the database and puts them in a list.  The list is iterated over, ignoring schedules
   * with end dates in the past or that have no respondents.  Each schedule is checked to see if they fall on the
   * schedule's frequency.  If they do, they are added to the list.
   *
   * @return a list of cleaned schedules that need to have occurrences generated.
   */
  protected List<Schedule> getSchedulesFromRepositoryAndProcess() {

    LocalDate todaysDate = LocalDate.now();
    List<Schedule> schedulesFromRepo = scheduleRepository.findAll();
    List<Schedule> processedSchedules = new ArrayList<>();
    Iterator<Schedule> itr = schedulesFromRepo.iterator();

    while (itr.hasNext()) {
      Schedule scheduleToCheck = itr.next();

      //ignore schedules if their respondents are null or their schedule's endDate has passed
      if (scheduleToCheck.getRespondents() == null
              || (scheduleToCheck.getEndDate() != null && scheduleToCheck.getEndDate().isBefore(todaysDate))) {
        continue;
      }

      //if today is on this schedule's frequency, add it to the list for occurrence generation
      else if (isTodayOnScheduleFrequency(scheduleToCheck)) {
        processedSchedules.add(scheduleToCheck);
      }
    }

    return processedSchedules;
  }
}

package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.httpclient.HttpClient;
import com.catalyst.overwatch.schedule.httpclient.jsonparser.JsonParser;
import com.catalyst.overwatch.schedule.model.*;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
import org.apache.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hmccardell on 7/18/2016.
 */
public class DailyJob implements Job{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private OccurrenceRepository occurrenceRepository;

    RestTemplate restTemplate = new RestTemplate();
    Logger logger = LogManager.getRootLogger();
    List<Occurrence> occurrencesToPost = new ArrayList<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        List<Schedule> cleanedSchedules = new ArrayList<>();
        cleanedSchedules = getSchedulesFromRepositoryAndProcess();
        occurrencesToPost = generateOccurrencesForToday(cleanedSchedules);
        occurrenceRepository.save(occurrencesToPost);

    }

    /**
     * Retrieves a list of schedules from the schedule repository that have today's date.
     * Schedules with null respondents are removed from the list, then the list is iterated
     * over and an occurrence is constructed for each respondent in each schedule.  These
     * occurrences are added to a list and returned.
     *
     * @return a list of occurrences for schedule respondents with a start date before or equal to today.
     */
    protected List<Occurrence> generateOccurrencesForToday(List<Schedule> passedListOfSchedules){

        String surveyLinkForThisRespondent = "";

        //Construct occurrences for each respondent, add them to a list to post.
        for(Schedule schedule : passedListOfSchedules){

            for(Respondent respondent : schedule.getRespondents()){
                Occurrence occurrenceToPost = new Occurrence(respondent);
                Occurrence postedOccurrence = occurrenceRepository.save(occurrenceToPost);
                surveyLinkForThisRespondent = buildSurveyLink(schedule.getTemplateURI(), postedOccurrence.getId());
                generateNotification(respondent.getUser().getEmail(), surveyLinkForThisRespondent);
                logger.info("Generate notification: " + respondent.getUser().getEmail() + "    link: " + surveyLinkForThisRespondent);
                logger.info(respondent.getUser());
            }
        }

        return occurrencesToPost;
    }

    /**
     * Determines the number of days between the start date of a schedule and today's date.
     * Uses the value of the schedule's frequency and the days between to decide if today's date falls on that frequency.
     * If the schedule's start date is today and the frequency is one time, then today is on the frequency.
     *
     * @param schedule the schedule to check.
     * @return boolean true when today's date falls on the schedule's frequency, else false.
     */
    protected Boolean isTodayOnScheduleFrequency(Schedule schedule){

        LocalDate todaysDate = LocalDate.now();
        boolean isOnFrequency = false;

        LocalDate thisScheduleStartDate = schedule.getStartDate();
        int weeksValueOfFrequency = schedule.getFrequency().getValue();
        long daysBetween = ChronoUnit.DAYS.between(thisScheduleStartDate, todaysDate);

        //Need a way to handle one shots.
        //Their startdate will be the day they get submitted, but daily process runs at 8am.
        //So their startdate will be in the past by the time they are checked.

        if(schedule.getFrequency().getValue() == 0){
            isOnFrequency = true;
            logger.info("The start date is today's date, so today is on that schedule's frequency.");
        }
        else if(daysBetween % weeksValueOfFrequency == 0){
            isOnFrequency = true;
            logger.info("Today's date lands on the schedule frequency." + schedule.getStartDate() + " " + schedule.getFrequency());
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
    protected List<Schedule> getSchedulesFromRepositoryAndProcess(){

        LocalDate todaysDate = LocalDate.now();
        List<Schedule> schedulesFromRepo = scheduleRepository.findAll();
        List<Schedule> processedSchedules = new ArrayList<>();
        Iterator<Schedule> itr = schedulesFromRepo.iterator();

        while(itr.hasNext()){
            Schedule scheduleToCheck = itr.next();

            //ignore schedules if their respondents are null or their schedule's endDate has passed
            if(scheduleToCheck.getRespondents() == null
                || (scheduleToCheck.getEndDate() != null && scheduleToCheck.getEndDate().isBefore(todaysDate))){
                continue;
            }

            //if today is on this schedule's frequency, add it to the list for occurrence generation
            else if(isTodayOnScheduleFrequency(scheduleToCheck)){
                processedSchedules.add(scheduleToCheck);
            }
        }

        return processedSchedules;
    }

    /**
     * Utilizes a surveySuid and an occurrence id to build a functional http link to a survey.
     *
     * @param surveySuid the id of the survey to build into the link
     * @param originatorId the occurrence id to include in the link
     * @return a valid link to a survey for a specific user's occurrence
     */
    protected String buildSurveyLink(String surveySuid, long originatorId){

        String base = NotificationConstants.FRONT_END_BASE_URL;
        String surveysSuidParam = NotificationConstants.SURVEYS_SUID_PARAM;
        String surveysOriginatorParam = NotificationConstants.SURVEYS_ORIGINATOR_PARAM;

        String link = base + surveysSuidParam + surveySuid;
        link += "&?";
        link += surveysOriginatorParam + originatorId;

        logger.info("build survey link: " + link);

        return link;
    }

    /**
     * Makes a restful call to the notifications service to generate an email to a user. This email will include
     * a clickable hyperlink that will take them to a survey they can fill out and submit.
     *
     * @param emailAddress the recipient's email address
     * @param surveyLink a survey link that includes their surveySuid and their occurrence id.
     */
    public void generateNotification(String emailAddress, String surveyLink) {

        String subject = NotificationConstants.SURVEY_WAITING_SUBJECT;
        String body = NotificationConstants.SURVEY_WAITING_BODY;
        body += "\n";
        body += "\n";
        body += "Link to survey: " + surveyLink;

        logger.info("INSIDE NOTIFICATIONS: " + emailAddress);

        String[] recipientAddress = new String[]{emailAddress};
        Notification notification = new Notification(recipientAddress, subject, body);

        if(emailAddress.equals("hmccardell@catalystdevworks.com")){
            try {
                restTemplate.postForEntity(NotificationConstants.NOTIFICATION_ENDPOINT, notification, Notification.class);
                logger.info("Generated email from rest template");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

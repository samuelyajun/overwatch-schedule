package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.httpclient.HttpClient;
import com.catalyst.overwatch.schedule.httpclient.jsonparser.JsonParser;
import com.catalyst.overwatch.schedule.model.*;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
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

    RestTemplate restTemplate = new RestTemplate();
    Logger logger = LogManager.getRootLogger();
    Notification notification;
    List<Occurrence> occurrencesToPost = new ArrayList<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        occurrencesToPost = generateOccurrencesForToday();
        generateNotifications();
    }

    /**
     * Retrieves a list of schedules from the schedule repository that have today's date.
     * Schedules with null respondents are removed from the list, then the list is iterated
     * over and an occurrence is constructed for each respondent in each schedule.  These
     * occurrences are added to a list and returned.
     *
     * @return a list of occurrences for schedule respondents with a start date before or equal to today.
     */
    protected List<Occurrence> generateOccurrencesForToday(){

        LocalDate todaysDate = LocalDate.now();
        List<Occurrence> occurrencesToPost = new ArrayList<>();

        List<Schedule> schedulesFromRepo = scheduleRepository.findAll();
        List<Schedule> schedulesToGenerate = new ArrayList<>();

        //Construct occurrences for each respondent, add them to a list to post.
        for(Schedule schedule : schedulesFromRepo){
            for(Respondent respondent : schedule.getRespondents()){
                Occurrence occurrence = new Occurrence(respondent);
                logger.info(occurrence);
                occurrencesToPost.add(occurrence);
            }
        }

        return occurrencesToPost;
    }

    protected Boolean isTodayOnScheduleFrequency(Schedule schedule){

        LocalDate todaysDate = LocalDate.now();
        boolean isOnFrequency = false;

        LocalDate thisSchedStartDate = schedule.getStartDate();
        int weeks = schedule.getFrequency().getValue();
        long daysBetween = ChronoUnit.DAYS.between(thisSchedStartDate, todaysDate);

        if(daysBetween % weeks == 0){
            isOnFrequency = true;
            logger.info("Today's date lands on the schedule frequency." + schedule.getStartDate() + " " + schedule.getFrequency());
        }

        return isOnFrequency;
    }

    protected List<Schedule> getSchedulesFromRepositoryAndProcess(){

        LocalDate todaysDate = LocalDate.now();
        List<Occurrence> occurrencesToPost = new ArrayList<>();

        List<Schedule> schedulesFromRepo = scheduleRepository.findAll();
        List<Schedule> schedulesToGenerate = new ArrayList<>();

        List<Schedule> listToReturn = new ArrayList<>();

        Iterator<Schedule> itr = schedulesFromRepo.iterator();

        //Process the results from the repository to remove schedules with list if their respondents are null or their schedule's endDate has passed
        while(itr.hasNext()){
            Schedule scheduleToCheck = itr.next();

            if(scheduleToCheck.getRespondents() == null
                    || scheduleToCheck.getEndDate().isBefore(todaysDate)){
                schedulesFromRepo.remove(scheduleToCheck);
            }  // if the enum value is ONE_TIME and the start date is today, add this to the generate occurrences list
            else if(scheduleToCheck.getFrequency().getValue() == 0 && scheduleToCheck.getStartDate() == todaysDate){
                schedulesToGenerate.add(scheduleToCheck);
            }
        }


        return listToReturn;
    }

    protected Boolean postOccurrences(List<Occurrence> occurrences){
        Boolean postedSuccessfully = false;

        return  postedSuccessfully;
    }

    protected String BuildSurveyLink(){
        String stuff = "";

        return stuff;
    }

    protected String retrieveLinkFromSurvey(){
        String link = "";

        return link;
    }


    static public void generateNotifications(){

        System.out.println("Generate notifications");
//        List<String> sendList = new ArrayList<>();
//        sendList.add("hmccardell@catalystdevworks.com");
//        String[] toList = sendList.toArray(new String[0]);
//
//        String subject = "Project Overwatch Daily Process Notification";
//        String body = "Bros and dudettes, our survey linkage magic would go in here.";
//
//        notification = new Notification(toList, subject, body);
//        try{
//            RestTemplate.post(NotificationConstants.NOTIFICATION_ENDPOINT, notification, Notification.class);
//            logger.info("sending notification");
//        } catch (HttpException e){
//            e.printStackTrace();
//        }
    }
}

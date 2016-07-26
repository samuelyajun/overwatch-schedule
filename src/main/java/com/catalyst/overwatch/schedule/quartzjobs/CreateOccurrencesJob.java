package com.catalyst.overwatch.schedule.quartzjobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.httpclient.HttpClient;
import com.catalyst.overwatch.schedule.httpclient.jsonparser.JsonParser;
import com.catalyst.overwatch.schedule.model.Notification;
import com.catalyst.overwatch.schedule.model.Schedule;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
import org.apache.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmccardell on 7/18/2016.
 */
public class CreateOccurrencesJob implements Job{

    @Autowired
    private ScheduleRepository scheduleRepository;

//
//    static HttpClient client = new HttpClient(new JsonParser());
//    static Logger logger = LogManager.getLogger();
//    static Notification notification;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("EXECUTING");
        //generateOccurancesByStartDate();
//        generateNotifications();

    }

    public void generateOccurancesByStartDate(){

        List<Schedule> schedules = new ArrayList<>();
        schedules = scheduleRepository.findAll();

        System.out.println(schedules);

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
//            client.post(NotificationConstants.NOTIFICATION_ENDPOINT, notification, Notification.class);
//            logger.info("sending notification");
//        } catch (HttpException e){
//            e.printStackTrace();
//        }
    }
}

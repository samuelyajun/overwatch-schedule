package com.catalyst.overwatch.schedule.quartzjobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.httpclient.HttpClient;
import com.catalyst.overwatch.schedule.httpclient.jsonparser.JsonParser;
import com.catalyst.overwatch.schedule.model.Notification;
import org.apache.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmccardell on 7/18/2016.
 */
public class CreateOccurrencesJob implements Job{

    HttpClient client = new HttpClient(new JsonParser());
    Notification notification;
    Logger logger = LogManager.getLogger();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        List<String> sendList = new ArrayList<>();
        sendList.add("hmccardell@catalystdevworks.com");
        String[] toList = sendList.toArray(new String[0]);

        String subject = "Project Overwatch Nightly Process Notification";
        String body = "Bros and dudettes, our survey linkage magic would go in here.";

        notification = new Notification(toList, subject, body);
        try{
            client.post(NotificationConstants.NOTIFICATION_ENDPOINT, notification, Notification.class);
            logger.info("sending notification");
        } catch (HttpException e){
            e.printStackTrace();
        }
    }

}

//package com.catalyst.overwatch.schedule.configuration;
//
//import com.catalyst.overwatch.schedule.quartzjobs.AutowiringSpringBeanJobFactory;
//import com.catalyst.overwatch.schedule.quartzjobs.CreateOccurrencesJob;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class QuartzConfig implements CommandLineRunner {
//
//    Logger logger = LogManager.getRootLogger();
//
//    public void run(String... args) throws SchedulerException {
//        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
//
//
//        JobDetail job = JobBuilder.newJob(CreateOccurrencesJob.class).withIdentity("nightlyProcess", "group-1").build();
//
//        Trigger trigger = TriggerBuilder
//                .newTrigger()
//                .withIdentity("nightly process", "group-1")
//                .withSchedule(
//                        //CronScheduleBuilder.cronSchedule("0 0 0 * * ?"))  //every day at midnight
//                        CronScheduleBuilder.cronSchedule("0/15 * * * * ?"))  //every day at midnight
//                .build();
//
//        //Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        Scheduler scheduler = new AutowiringSpringBeanJobFactory().createJobInstance();
//
//        scheduler.start();
//        scheduler.scheduleJob(job, trigger);
//    }
//}
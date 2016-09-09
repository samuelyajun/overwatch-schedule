package com.catalyst.overwatch.schedule.quartz.config;

/**
 * Created by hmccardell on 8/9/2016.
 */
public class SchedulerConstants {

  //Cron Trigger Documentation
  //http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html

  //These cron expressions are set to UTC time because the Docker containers' system time is 
  //UTC time and our efforts so far to configure the containers' time setting has been futile
	
  public static final String EIGHT_AM_EASTERN_EVERY_DAY = "0 0 12 1/1 * ? *";  //8:00 AM EST, every day
  public static final String FOUR_THIRTY_PM_EASTERN_EVERY_DAY = "0 30 20 1/1 * ? *";  //4:30 PM EST, every day
  public static final String EVERY_15_SECONDS = "0/15 * * * * ? *";
  public static final String JUST_AFTER_MIDNIGHT_EVERY_DAY = "0 1 7 1/1 * ? *";
  public static final String EVERY_30_SECONDS = "15/30 * * * * ? *";
}

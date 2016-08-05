package com.catalyst.overwatch.schedule.constants;

/**
 * Created by hmccardell on 7/20/2016.
 */
public class NotificationConstants {

  public static final String NOTIFICATION_ENDPOINT = "http://localhost:8080/notify";
  public static final String FRONT_END_BASE_URL = "http://localhost:3000";

  public static final String SURVEYS_ORIGINATOR_PARAM = "originatorId=";

  public static final String SURVEY_WAITING_SUBJECT = "Project Overwatch Notification:  Survey Waiting";
  public static final String SURVEY_WAITING_BODY = "Please fill out this survey. I don't know what the nags and tattles will entail, but it's not good";

  public static final String NAG_BODY = "Your feedback is vital and we are waiting to hear from you! Before the end of the day, please take a few minutes to fill out your survey.";

  public static final String TATTLE_SUBJECT = "Incomplete surveys on your team";
  public static final String TATTLE_BODY_BEGIN = "We know your team is very busy and hard at work, but their feedback is important to us. Here is a list of your team members who have not yet responded to the";
  public static final String TATTLE_BODY_END = "Please see to it that all outstanding surveys are completed today.";


}

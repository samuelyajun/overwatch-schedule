package com.catalyst.overwatch.schedule.constants;

/**
 * Created by hmccardell on 7/20/2016.
 */
public class NotificationConstants {

  //SURVEY RESPONSE SERVICE
  public static final String RESPONSE_BASE_URL = "http://localhost:8050";
  public static final String SURVEY_RESPONSE = "/surveyResponses";
  public static final String QUERY_BY_DATE = "/search/findSurveyResponseByDateAnswered?dateAnswered=";
  public static final String SEARCH_SURVEY_RESPONSE_BY_DATE = RESPONSE_BASE_URL + SURVEY_RESPONSE + QUERY_BY_DATE;

  //NOTIFICATIONS SERVICE
  public static final String NOTIFICATION_ENDPOINT = "http://localhost:8080/notify";

  public static final String TATTLE_SUBJECT = "Incomplete surveys on your team.";
  public static final String TATTLE_BODY_BEGIN = "We know your team is very busy and hard at work, but their feedback is important to us. Here is a list of your team members who have not yet responded to the";
  public static final String TATTLE_BODY_END = "Please see to it that all outstanding surveys are completed today.";

  public static final String SURVEYS_ORIGINATOR_PARAM = "originatorId=";
  public static final String SURVEY_WAITING_SUBJECT = "Project Overwatch Notification:  Survey Waiting";
  public static final String SURVEY_WAITING_BODY = "Please fill out this survey. I don't know what the nags and tattles will entail, but it's not good";

  public static final String NAG_BODY = "Your feedback is vital and we are waiting to hear from you! Before the end of the day, please take a few minutes to fill out your survey.";

  public static final String SURVEY_SUBJECT_SPRINT_CHECKUP = "Sprint Checkup Survey";
  public static final String SURVEY_SUBJECT_SPD_TEAM = "Sprint Planning Day Survey";
  public static final String SURVEY_SUBJECT_SPD_LEADERS = "Sprint Planning Day Survey for Leads";
  public static final String SURVEY_SUBJECT_EM_TL_QUANTITATIVE = "Delivery Data Reporting Survey";

  public static final String SURVEY_BODY_SPRINT_CHECKUP = "We want to know how your sprint is going!  Please take a few minutes this morning to respond to the following brief survey.";
  public static final String SURVEY_BODY_SPD_TEAM = "How do you feel about your upcoming sprint? Please let us know how things are going by responding to the following survey.";
  public static final String SURVEY_BODY_SPD_LEADERS = "We value your perspective as a leader. Once today’s sprint ceremonies are complete, please let us know how things are going by responding to the following survey.";
  public static final String SURVEY_BODY_EM_TL_QUANTITATIVE = "In order to assess project health, we need to gather key metrics from our teams. Please take a few moments today to provide the requested information about your most recent sprint.";
}

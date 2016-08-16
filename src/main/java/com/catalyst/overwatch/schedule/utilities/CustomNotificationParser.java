package com.catalyst.overwatch.schedule.utilities;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;

/**
 * Takes in the template name, modifies the string and selects a constant
 * for the email subject and body
 *
 * @author Bradley Larsen <blarsen@catalystdevworks.com>
 * @version 1.0
 * @since 1.0
 */
public class CustomNotificationParser {


    /**
     * Takes in the template name and transforms the string to the format
     * in the Notification Constants file and selects a value to be put in
     * the email subject
     *
     * @param templateName from the current survey
     * @return the matching constant from NotificationConstants
     */
    public static String notificationSubjectParser(String templateName) {

        String parsedTemplateName = templateName.replaceAll("\\s", "_").toUpperCase();

        String surveySubject = "SURVEY_SUBJECT_" + parsedTemplateName;

        String surveySubjectValue = null;

        switch (surveySubject) {

            case "SURVEY_SUBJECT_SPRINT_CHECKUP":
                surveySubjectValue = NotificationConstants.SURVEY_SUBJECT_SPRINT_CHECKUP;
                break;

            case "SURVEY_SUBJECT_SPRINT_PLANNING":
                surveySubjectValue = NotificationConstants.SURVEY_SUBJECT_SPRINT_PLANNING;
                break;

            case "SURVEY_SUBJECT_SPRINT_PLANNING_LEADS":
                surveySubjectValue = NotificationConstants.SURVEY_SUBJECT_SPRINT_PLANNING_LEADS;
                break;

            case "SURVEY_SUBJECT_QUANTITATIVE":
                surveySubjectValue = NotificationConstants.SURVEY_SUBJECT_QUANTITATIVE;
                break;

        }

        return surveySubjectValue;

    }

    /**
     * Takes in the template name and transforms the string to the format
     * in the Notification Constants file and selects a value to be put in
     * the email body
     *
     * @param templateName from the current survey
     * @return the matching constant from NotificationConstants
     */
    public static String notificationBodyParser(String templateName){

        String parsedTemplateName = templateName.replaceAll("\\s", "_").toUpperCase();


        String surveyBody = "SURVEY_BODY_" + parsedTemplateName;

        String surveyBodyValue = null;

        switch (surveyBody) {

            case "SURVEY_BODY_SPRINT_CHECKUP":
                surveyBodyValue = NotificationConstants.SURVEY_BODY_SPRINT_CHECKUP;
                break;

            case "SURVEY_BODY_SPRINT_PLANNING":
                surveyBodyValue = NotificationConstants.SURVEY_BODY_SPRINT_PLANNING;
                break;

            case "SURVEY_BODY_SPRINT_PLANNING_LEADS":
                surveyBodyValue = NotificationConstants.SURVEY_BODY_SPRINT_PLANNING_LEADS;
                break;

            case "SURVEY_BODY_QUANTITATIVE":
                surveyBodyValue = NotificationConstants.SURVEY_BODY_QUANTITATIVE;
                break;

        }

        return surveyBodyValue;

    }

}
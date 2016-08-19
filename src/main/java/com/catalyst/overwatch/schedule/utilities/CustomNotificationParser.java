package com.catalyst.overwatch.schedule.utilities;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import org.slf4j.Logger;

/**
 * Takes in the template name, modifies the string and selects a constant
 * for the email subject and body
 *
 * @author Bradley Larsen <blarsen@catalystdevworks.com>
 * @version 1.0
 * @since 1.0
 */
public class CustomNotificationParser {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CustomNotificationParser.class);


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

            case "SURVEY_SUBJECT_SPD_TEAM":
                surveySubjectValue = NotificationConstants.SURVEY_SUBJECT_SPD_TEAM;
                break;

            case "SURVEY_SUBJECT_SPD_LEADERS":
                surveySubjectValue = NotificationConstants.SURVEY_SUBJECT_SPD_LEADERS;
                break;

            case "SURVEY_SUBJECT_EM_QUANTITATIVE":
                surveySubjectValue = NotificationConstants.SURVEY_SUBJECT_EM_TL_QUANTITATIVE;
                break;

            case "SURVEY_SUBJECT_TL_QUANTITATIVE":
                surveySubjectValue = NotificationConstants.SURVEY_SUBJECT_EM_TL_QUANTITATIVE;
                break;

            default:
                surveySubjectValue = "SUBJECT N/A";
                LOGGER.info("Notification Subject Error: schedule.getTemplateName returned a value that does not match any of the Notification Constants");


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

            case "SURVEY_BODY_SPD_TEAM":
                surveyBodyValue = NotificationConstants.SURVEY_BODY_SPD_TEAM;
                break;

            case "SURVEY_BODY_SPD_LEADERS":
                surveyBodyValue = NotificationConstants.SURVEY_BODY_SPD_LEADERS;
                break;

            case "SURVEY_BODY_EM_QUANTITATIVE":
                surveyBodyValue = NotificationConstants.SURVEY_BODY_EM_TL_QUANTITATIVE;
                break;

            case "SURVEY_BODY_TL_QUANTITATIVE":
                surveyBodyValue = NotificationConstants.SURVEY_BODY_EM_TL_QUANTITATIVE;
                break;

            default:
                surveyBodyValue = "SUBJECT N/A";
                LOGGER.info("Notification Body Error: schedule.getTemplateName returned a value that does not match any of the Notification Constants");

        }

        return surveyBodyValue;

    }

}
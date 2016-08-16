package com.catalyst.overwatch.schedule.utilities;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the CustomNotificationParser class
 *
 * @author Bradley Larsen <blarsen@catalystdevworks.com>
 * @version 1.0
 * @since 1.0
 */
public class CustomNotificationParserTest {

    private String CHECKUP = "Sprint Checkup";
    private String PLANNING = "Sprint Planning";
    private String PLANNING_LEADS = "Sprint Planning Leads";
    private String QUANTITATIVE = "Quantitative";

    @Test
    public void notificationSubjectParserTestSwitchCaseSubjectSprintCheckup(){

        String expected = NotificationConstants.SURVEY_SUBJECT_SPRINT_CHECKUP;

        String actual = CustomNotificationParser.notificationSubjectParser(CHECKUP);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notificationSubjectParserTestSwitchCaseSubjectSprintPlanning(){

        String expected = NotificationConstants.SURVEY_SUBJECT_SPRINT_PLANNING;

        String actual = CustomNotificationParser.notificationSubjectParser(PLANNING);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notificationSubjectParserTestSwitchCaseSubjectSprintPlanningLeads(){

        String expected = NotificationConstants.SURVEY_SUBJECT_SPRINT_PLANNING_LEADS;

        String actual = CustomNotificationParser.notificationSubjectParser(PLANNING_LEADS);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notificationSubjectParserTestSwitchCaseSubjectQuantitative(){

        String expected = NotificationConstants.SURVEY_SUBJECT_QUANTITATIVE;

        String actual = CustomNotificationParser.notificationSubjectParser(QUANTITATIVE);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notificationBodyParserTestSwitchCaseSprintCheckup(){

        String expected = NotificationConstants.SURVEY_BODY_SPRINT_CHECKUP;

        String actual = CustomNotificationParser.notificationBodyParser(CHECKUP);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void notificationBodyParserTestSwitchCaseSprintPlanning(){

        String expected = NotificationConstants.SURVEY_BODY_SPRINT_PLANNING;

        String actual = CustomNotificationParser.notificationBodyParser(PLANNING);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void notificationBodyParserTestSwitchCaseSprintPlanningLeads(){

        String expected = NotificationConstants.SURVEY_BODY_SPRINT_PLANNING_LEADS;

        String actual = CustomNotificationParser.notificationBodyParser(PLANNING_LEADS);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void notificationBodyParserTestSwitchCaseQuantitative(){

        String expected = NotificationConstants.SURVEY_BODY_QUANTITATIVE;

        String actual = CustomNotificationParser.notificationBodyParser(QUANTITATIVE);

        Assert.assertEquals(expected, actual);
    }
}

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
    private String PLANNING = "SPD Team";
    private String PLANNING_LEADS = "SPD Leaders";
    private String EM_QUANTITATIVE = "EM Quantitative";
    private String TL_QUANTITATIVE = "TL Quantitative";
    private String DEFAULT = "SUBJECT N/A";
    private String TEST_STRING = "test words";

    @Test
    public void notificationSubjectParserTestSwitchCaseSubjectSprintCheckup(){

        String expected = NotificationConstants.SURVEY_SUBJECT_SPRINT_CHECKUP;

        String actual = CustomNotificationParser.notificationSubjectParser(CHECKUP);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notificationSubjectParserTestSwitchCaseSubjectSprintPlanning(){

        String expected = NotificationConstants.SURVEY_SUBJECT_SPD_TEAM;

        String actual = CustomNotificationParser.notificationSubjectParser(PLANNING);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notificationSubjectParserTestSwitchCaseSubjectSprintPlanningLeads(){

        String expected = NotificationConstants.SURVEY_SUBJECT_SPD_LEADERS;

        String actual = CustomNotificationParser.notificationSubjectParser(PLANNING_LEADS);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notificationSubjectParserTestSwitchCaseSubjectEMQuantitative(){

        String expected = NotificationConstants.SURVEY_SUBJECT_EM_TL_QUANTITATIVE;

        String actual = CustomNotificationParser.notificationSubjectParser(EM_QUANTITATIVE);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notificationSubjectParserTestSwitchCaseSubjectTLQuantitative(){

        String expected = NotificationConstants.SURVEY_SUBJECT_EM_TL_QUANTITATIVE;

        String actual = CustomNotificationParser.notificationSubjectParser(TL_QUANTITATIVE);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void notificationSubjectParserTestSwitchCaseDefault(){

        String expected = DEFAULT;

        String actual = CustomNotificationParser.notificationSubjectParser(TEST_STRING);

        Assert.assertEquals(expected, actual);

    }

    //BODY TESTS

    @Test
    public void notificationBodyParserTestSwitchCaseSprintCheckup(){

        String expected = NotificationConstants.SURVEY_BODY_SPRINT_CHECKUP;

        String actual = CustomNotificationParser.notificationBodyParser(CHECKUP);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void notificationBodyParserTestSwitchCaseSprintPlanning(){

        String expected = NotificationConstants.SURVEY_BODY_SPD_TEAM;

        String actual = CustomNotificationParser.notificationBodyParser(PLANNING);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void notificationBodyParserTestSwitchCaseSprintPlanningLeads(){

        String expected = NotificationConstants.SURVEY_BODY_SPD_LEADERS;

        String actual = CustomNotificationParser.notificationBodyParser(PLANNING_LEADS);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void notificationBodyParserTestSwitchCaseEMQuantitative(){

        String expected = NotificationConstants.SURVEY_BODY_EM_TL_QUANTITATIVE;

        String actual = CustomNotificationParser.notificationBodyParser(EM_QUANTITATIVE);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void notificationBodyParserTestSwitchTLCaseQuantitative(){

        String expected = NotificationConstants.SURVEY_BODY_EM_TL_QUANTITATIVE;

        String actual = CustomNotificationParser.notificationBodyParser(TL_QUANTITATIVE);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void notificationBodyParserTestSwitchDefault(){

        String expected = DEFAULT;

        String actual = CustomNotificationParser.notificationBodyParser(TEST_STRING);

        Assert.assertEquals(expected, actual);
    }

}

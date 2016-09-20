package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.constants.Urls;
import com.catalyst.overwatch.schedule.model.*;
import com.catalyst.overwatch.schedule.repository.FlightRepository;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Test the TattlesJob class
 *
 * @author arajigah
 */

@RunWith(MockitoJUnitRunner.class)
public class TattlesJobTest {

    private static final String TEST_STRING = "test";
    private static final long TEST_LONG = 1;
    private static final String VALUE = "Engagement Manager";

    @InjectMocks
    private TattlesJob testTattlesJob;

    @Mock
    private RestTemplate mockRestTemplate;

    @Mock
    private ScheduleRepository mockScheduleRepository;

    @Mock
    private FlightRepository mockFlightRepository;

    @Mock
    private OccurrenceRepository testOccurrenceRepository;

    @Mock
    private Schedule mockSchedule;

    @Mock
    private Occurrence mockOccurrence;
    
    @Mock
    private Urls mockUrls;

    @Mock
    private AllowedAttribute mockAttribute;

    @Mock
    private Urls urls;

    private List<Occurrence> testArrayOccurrences ;
    private List<Occurrence> testTattleOnList;
    private NotificationConstants notificationConstants;
    private User testUser1;
    private User testUser2;
    private User testUser3;
    private User testUser4;
    private Respondent testRespondent1;
    private Respondent testRespondent2;
    private Respondent testRespondent3;
    private Respondent testRespondent4;
    private Occurrence testOccurrence1;
    private Occurrence testOccurrence2;
    private Occurrence testOccurrence3;
    private Occurrence testOccurrence4;
    private Schedule testSchedule;
    private Flight testFlight;
    private Set<AllowedAttribute> testAllowedAttributes1;
    private Set<AllowedAttribute> testAllowedAttributes2;
    private Set<AllowedAttribute> testAllowedAttributes3;
    private Set<AllowedAttribute> testAllowedAttributes4;
    private AllowedAttribute  testAllowedAttribute1;
    private AllowedAttribute  testAllowedAttribute2;
    private AllowedAttribute  testAllowedAttribute3;
    private AllowedAttribute  testAllowedAttribute4;
    private AttributeType testAttributeType;
    private HashSet<Respondent> testSetRespondent;
    private ArrayList<Respondent> testTattleToList;
    private List<Respondent> testRespondentList;
    private Set<Respondent> testCheckSet = new HashSet<>();
    private Set<Respondent> manualRespondent = new HashSet<Respondent>();

    @Before
    public void testSetup() {
        MockitoAnnotations.initMocks(this);
        testArrayOccurrences = new ArrayList<Occurrence>();
        testUser1 = new User();
        testUser2= new User();
        testUser3 = new User();
        testUser4 = new User();
        testRespondent1 = new Respondent();
        testRespondent2 = new Respondent();
        testRespondent3 = new Respondent();
        testRespondent4 = new Respondent();
        testOccurrence1 = new Occurrence();
        testOccurrence2 = new Occurrence();
        testOccurrence3 = new Occurrence();
        testOccurrence4 = new Occurrence();
        testSchedule = new Schedule();
        testFlight = new Flight();
        testTattleOnList = new ArrayList<>();
        testTattleToList = new ArrayList<>();
        testCheckSet = new HashSet<>();
        testAllowedAttributes1 = new HashSet<>();
        testAllowedAttributes2 = new HashSet<>();
        testAllowedAttributes3 = new HashSet<>();
        testAllowedAttributes4 = new HashSet<>();
        testAllowedAttribute1 = new AllowedAttribute();
        testAllowedAttribute2 = new AllowedAttribute();
        testAllowedAttribute3 = new AllowedAttribute();
        testAllowedAttribute4 = new AllowedAttribute();
        testAttributeType = new AttributeType();
        testSetRespondent = new HashSet<>();

        // Test Attribute Type - ROLE
        testAttributeType.setAttributeTypeId(1);
        testAttributeType.setName("ROLE");

        // Allowed Attributes for Respondents - ROLES
        testAllowedAttribute1.setId(1);
        testAllowedAttribute1.setAttributeValue("Engagement Manager");
        testAllowedAttribute1.setAttributeType(testAttributeType);

        testAllowedAttribute2.setId(2);
        testAllowedAttribute2.setAttributeValue("Tech Lead");
        testAllowedAttribute2.setAttributeType(testAttributeType);

        testAllowedAttribute3.setId(3);
        testAllowedAttribute3.setAttributeValue("Business Analyst");
        testAllowedAttribute3.setAttributeType(testAttributeType);

        testAllowedAttribute4.setId(4);
        testAllowedAttribute4.setAttributeValue("Developer");
        testAllowedAttribute4.setAttributeType(testAttributeType);

        // SET of AllowedAttributes
        testAllowedAttributes1.add(testAllowedAttribute1);//EM
        testAllowedAttributes2.add(testAllowedAttribute2);//TL
        testAllowedAttributes3.add(testAllowedAttribute3);//BA
        testAllowedAttributes4.add(testAllowedAttribute4);//DEV

        // Test Users
        testUser1.setId(1);
        testUser1.setFirstName("Kate");
        testUser1.setLastName("Slott");
        testUser1.setEmail("kslott@catalystdevworks.com");

        testUser2.setId(2);
        testUser2.setFirstName("Abby");
        testUser2.setLastName("Edwards");
        testUser2.setEmail("aedwards@catalystdevworks.com");

        testUser3.setId(3);
        testUser3.setFirstName("Adam");
        testUser3.setLastName("Fields");
        testUser3.setEmail("afields@catalystdevworks.com");

        testUser4.setId(4);
        testUser4.setFirstName("Brad");
        testUser4.setLastName("Larsen");
        testUser4.setEmail("blarsen@catalystdevworks.com");

        // Test Respondents
        testRespondent1.setUser(testUser1);
        testRespondent1.setId(TEST_LONG);
        testRespondent1.setAllowedAttributes(testAllowedAttributes1);

        testRespondent2.setUser(testUser2);
        testRespondent2.setId(TEST_LONG);
        testRespondent2.setAllowedAttributes(testAllowedAttributes2);

        testRespondent3.setUser(testUser3);
        testRespondent3.setId(TEST_LONG);
        testRespondent3.setAllowedAttributes(testAllowedAttributes3);

        testRespondent4.setUser(testUser4);
        testRespondent4.setId(TEST_LONG);
        testRespondent4.setAllowedAttributes(testAllowedAttributes4);

        // SET of Respondents
        testSetRespondent.add(testRespondent1);
        testSetRespondent.add(testRespondent2);
        testSetRespondent.add(testRespondent3);
        testSetRespondent.add(testRespondent4);

        // Test occurrence for EM
        testOccurrence1.setId(TEST_LONG);
        testOccurrence1.setRespondent(testRespondent1);
        testOccurrence1.setScheduleId(TEST_LONG);
        testOccurrence1.setIsComplete(true);
        testOccurrence1.setGenerationDate(LocalDate.now());
        testOccurrence1.setFlightNumber(TEST_LONG);

        // Test occurrence for TL
        testOccurrence2.setId(TEST_LONG);
        testOccurrence2.setRespondent(testRespondent2);
        testOccurrence2.setScheduleId(TEST_LONG);
        testOccurrence2.setIsComplete(true);
        testOccurrence2.setGenerationDate(LocalDate.now());
        testOccurrence2.setFlightNumber(TEST_LONG);

        // Test occurrence for BA
        testOccurrence3.setId(TEST_LONG);
        testOccurrence3.setRespondent(testRespondent3);
        testOccurrence3.setScheduleId(TEST_LONG);
        testOccurrence3.setIsComplete(true);
        testOccurrence3.setGenerationDate(LocalDate.now());
        testOccurrence3.setFlightNumber(TEST_LONG);

        // Test occurrence for DEV
        testOccurrence4.setId(TEST_LONG);
        testOccurrence4.setRespondent(testRespondent4);
        testOccurrence4.setScheduleId(TEST_LONG);
        testOccurrence4.setIsComplete(true);
        testOccurrence4.setGenerationDate(LocalDate.now());
        testOccurrence4.setFlightNumber(TEST_LONG);

        // Test Occurrence array
        testArrayOccurrences.add(testOccurrence1);
        testArrayOccurrences.add(testOccurrence2);
        testArrayOccurrences.add(testOccurrence3);
        testArrayOccurrences.add(testOccurrence4);

        // Test Schedule
        testSchedule.setTemplateName(TEST_STRING);
        testSchedule.setRespondents(testSetRespondent);

        // Test Flight
        testFlight.setScheduleId(TEST_LONG);
        testFlight.setFlightNumber(TEST_LONG);
        testFlight.setId(TEST_LONG);
        testFlight.setScheduleIsActive(true);
        testFlight.setIsClosed(false);



        when(testOccurrenceRepository.findByScheduleIdAndFlightNumber(TEST_LONG, TEST_LONG))
                .thenReturn(testArrayOccurrences);


        when(mockScheduleRepository.findById(anyLong())).thenReturn(testSchedule);

        when(mockScheduleRepository.findByRespondentsId(TEST_LONG)).thenReturn(testSchedule);

        when(mockUrls.getNotificationEndpoint()).thenReturn(TEST_STRING);

        when(mockRestTemplate.getForObject(anyString(), any())).thenReturn(testRespondent1);
    }

    @Test
    public void buildTattleBodyTest() {
        testOccurrence1.setIsComplete(false);
        testOccurrence4.setIsComplete(false);
        String actual =  testTattlesJob.buildTattleBody(testArrayOccurrences);
        StringBuilder testUsersString = new StringBuilder();
        for (Occurrence occurrence : testArrayOccurrences) {
            if(occurrence.getIsComplete() == false){
                testUsersString.append(occurrence.getRespondent().getUser().getFirstName() + " " +
                        occurrence.getRespondent().getUser().getLastName() + "\n");
            } else {
                System.out.println(occurrence.getRespondent().getUser().getFirstName() + " is complete");
            }
        }
        System.out.println("actual  "  + actual);

        StringBuilder expected = new StringBuilder(notificationConstants.TATTLE_BODY_BEGIN).append(" ").append(testSchedule.getTemplateName())
               .append(": ").append("\n\n").append(testUsersString).append("\n").append(notificationConstants.TATTLE_BODY_END);
        Assert.assertEquals(expected.toString(), actual);
    }

    @Test
    public void calculateThresholdForFlightTestTrue() {
        when(urls.getReportEndpoint()).thenReturn("fake url");
        testTattlesJob.calculateThresholdForFlight(testFlight);
        verify(mockFlightRepository,times(1)).save(any(Flight.class));
    }

    @Test
    public void calculateThresholdForFlightTestFalse() {
        when(urls.getReportEndpoint()).thenReturn("fake url");
        testOccurrence4.setIsComplete(false);
        testTattlesJob.calculateThresholdForFlight(testFlight);
        verify(mockScheduleRepository,times(1)).findByRespondentsId((anyLong()));
    }

    @Test
    public void determineTattleRecipientIsEMAndTLTest(){
         testRespondentList  = testTattlesJob.determineTattleRecipients(testSchedule);
         testCheckSet.addAll(testRespondentList);
        for (Respondent testRespondent: testCheckSet){
            for(AllowedAttribute testAllowedAttribute: testRespondent.getAllowedAttributes() ){
                if(testAllowedAttribute.getAttributeValue().equals("Tech Lead")) {
                    Assert.assertEquals(testAllowedAttribute.getAttributeValue(),"Tech Lead");
                } else {
                    Assert.assertEquals(testAllowedAttribute.getAttributeValue(),"Engagement Manager");
                }

            }
        }
    }

   @Test
    public void determineTattleRecipientIsNotEMTest(){
       testRespondentList  = testTattlesJob.determineTattleRecipients(testSchedule);
       System.out.println(testRespondentList);
       testCheckSet.addAll(testRespondentList);
       for (Respondent testRespondent: testCheckSet){
           for(AllowedAttribute testAllowedAttribute: testRespondent.getAllowedAttributes() ){
               Assert.assertNotEquals(testAllowedAttribute.getAttributeValue(),"Developer");
               Assert.assertNotEquals(testAllowedAttribute.getAttributeValue(),"Business Analyst");
           }
       }
    }
}

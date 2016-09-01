package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.model.*;
import com.catalyst.overwatch.schedule.repository.FlightRepository;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test the TattlesJob class
 *
 * @author arajigah
 */

@RunWith(MockitoJUnitRunner.class)
public class TattlesJobTest {

    @InjectMocks
    private TattlesJob testTattlesJob;

    private static final String TEST_STRING = "test";
    private static final long TEST_LONG = 1;

    @Mock
    private RestTemplate mockRestTemplate;

//    @Mock
//    private OccurrenceRepository mockOccurrenceRepository;

    @Mock
    private ScheduleRepository mockScheduleRepository;

    @Mock
    private FlightRepository mockFlightRepository;

  //  @Mock
  //  private Urls mockUrls;

    @Mock
    private Schedule mockSchedule;

    @Mock
    private Occurrence mockOccurrence;

    private User mockUser;

    @Mock
   // private Respondent mockRespondent;

    private List<Occurrence> testArrayOccurrences ; // = new ArrayList<Occurrence>();
    private NotificationConstants notificationConstants;
    private User testUser;
    private Respondent testRespondent;
    private Occurrence testOccurrence;
    private Schedule testSchedule;
    private Flight testFlight;

    @Mock
    private OccurrenceRepository testOccurrenceRepository;

    private FlightRepository testFlightRespository;
    private LocalDate testGenerationDate;
    private Collection<Occurrence> testCollection;

    @Before
    public void testSetup(){
        MockitoAnnotations.initMocks(this);
        testArrayOccurrences = new ArrayList<Occurrence>();
        testUser = new User();
        testRespondent = new Respondent();
        testOccurrence = new Occurrence();
        testSchedule = new Schedule();
        testFlight = new Flight();

        testUser.setLastName(TEST_STRING); //move to before
        testOccurrence.setId(TEST_LONG);
        testUser.setId(TEST_LONG);
        testUser.setFirstName(TEST_STRING);
        testUser.setEmail(TEST_STRING);
        testRespondent.setUser(testUser);
        testOccurrence.setRespondent(testRespondent);
        testOccurrence.setScheduleId(TEST_LONG);
        testSchedule.setTemplateName(TEST_STRING);

        testFlight.setScheduleId(TEST_LONG);  //see before
        testFlight.setFlightNumber(TEST_LONG);
        testFlight.setId(TEST_LONG);
        testFlight.setScheduleIsActive(true);  //need this?
        testFlight.setIsClosed(true);   //need this?
        testOccurrence.setIsComplete(true);
        testOccurrence.setGenerationDate(LocalDate.now());
        testOccurrence.setFlightNumber(TEST_LONG);
        testOccurrence.setFlightNumber(TEST_LONG);
        testRespondent.setUser(testUser);
        testUser.setEmail(TEST_STRING);

        testArrayOccurrences.add(testOccurrence);

       // testOccurrenceRepository = new OccurrenceRepository();
       // testFlightRespository = new FlightRepository();
    }

    @Test
    public void buildTattleBodyTest() {

        when(mockScheduleRepository.findById(anyLong())).thenReturn(testSchedule);

        String actual =  testTattlesJob.buildTattleBody(testArrayOccurrences);
        StringBuilder userString = new StringBuilder(testUser.getFirstName() + " " + testUser.getLastName() + "\n");
        StringBuilder expected = new StringBuilder(notificationConstants.TATTLE_BODY_BEGIN).append(" ").append(testSchedule.getTemplateName())
                .append(": ").append("\n\n").append(userString).append("\n").append(notificationConstants.TATTLE_BODY_END);
        Assert.assertEquals(expected.toString(), actual);

    }

    @Test
    public void calculateThresholdForFlightTestTrue() {

        when(testOccurrenceRepository.findByScheduleIdAndFlightNumber(TEST_LONG,
                TEST_LONG)).thenReturn(testArrayOccurrences);

        testTattlesJob.calculateThresholdForFlight(testFlight);

        Assert.assertTrue(testFlight.getIsClosed() == true);

    }

}

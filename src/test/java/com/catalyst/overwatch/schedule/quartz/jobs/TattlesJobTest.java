package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.constants.Urls;
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
import java.util.*;

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

    private List<Occurrence> testArrayOccurrences ;
    private List<Occurrence> testTattleOnList;
    private NotificationConstants notificationConstants;
    private User testUser;
    private Respondent testRespondent;
    private Occurrence testOccurrence;
    private Schedule testSchedule;
    private Flight testFlight;

    @Before
    public void testSetup(){
        MockitoAnnotations.initMocks(this);
        testArrayOccurrences = new ArrayList<Occurrence>();
        testUser = new User();
        testRespondent = new Respondent();
        testOccurrence = new Occurrence();
        testSchedule = new Schedule();
        testFlight = new Flight();
        testTattleOnList = new ArrayList<>();

        testUser.setLastName(TEST_STRING);
        testUser.setId(TEST_LONG);
        testUser.setFirstName(TEST_STRING);
        testUser.setEmail(TEST_STRING);

        testOccurrence.setId(TEST_LONG);
        testOccurrence.setRespondent(testRespondent);
        testOccurrence.setScheduleId(TEST_LONG);
        testOccurrence.setIsComplete(true);
        testOccurrence.setGenerationDate(LocalDate.now());
        testOccurrence.setFlightNumber(TEST_LONG);
        testOccurrence.setFlightNumber(TEST_LONG);

        testRespondent.setUser(testUser);
        testRespondent.setId(1);
        testRespondent.setAllowedAttributes(new HashSet<>());
        testRespondent.setUser(testUser);

        testSchedule.setTemplateName(TEST_STRING);

        testFlight.setScheduleId(TEST_LONG);
        testFlight.setFlightNumber(TEST_LONG);
        testFlight.setId(TEST_LONG);
        testFlight.setScheduleIsActive(true);
        testFlight.setIsClosed(true);

        testArrayOccurrences.add(testOccurrence);

        when(testOccurrenceRepository.findByScheduleIdAndFlightNumber(TEST_LONG,
                TEST_LONG)).thenReturn(testArrayOccurrences);

        Set<Respondent> manualRespondent = new HashSet<Respondent>();

        manualRespondent.add(testRespondent);

        testSchedule.setRespondents(manualRespondent);

        when(mockScheduleRepository.findById(anyLong())).thenReturn(testSchedule);

        when(mockScheduleRepository.findByRespondentsId(TEST_LONG)).thenReturn(testSchedule);
        
        when(mockUrls.getNotificationEndpoint()).thenReturn(TEST_STRING);
        
        when(mockRestTemplate.getForObject(anyString(), any())).thenReturn(testRespondent);
    }

    @Test
    public void buildTattleBodyTest() {
        String actual =  testTattlesJob.buildTattleBody(testArrayOccurrences);
        StringBuilder userString = new StringBuilder(testUser.getFirstName() + " " + testUser.getLastName() + "\n");
        StringBuilder expected = new StringBuilder(notificationConstants.TATTLE_BODY_BEGIN).append(" ").append(testSchedule.getTemplateName())
                .append(": ").append("\n\n").append(userString).append("\n").append(notificationConstants.TATTLE_BODY_END);
        Assert.assertEquals(expected.toString(), actual);
    }

    @Test
    public void calculateThresholdForFlightTestTrue() {
        testTattlesJob.calculateThresholdForFlight(testFlight);
        verify(mockFlightRepository,times(1)).save(any(Flight.class));
    }

    @Test
    public void calculateThresholdForFlightTestFalse() {
        testOccurrence.setIsComplete(false);
        testTattlesJob.calculateThresholdForFlight(testFlight);
        verify(mockScheduleRepository,times(1)).findByRespondentsId((anyLong()));
    }
}

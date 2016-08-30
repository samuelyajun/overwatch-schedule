package com.catalyst.overwatch.schedule.quartz.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.client.RestTemplate;

import com.catalyst.overwatch.schedule.constants.Urls;
import com.catalyst.overwatch.schedule.exceptions.OverwatchScheduleException;
import com.catalyst.overwatch.schedule.model.Flight;
import com.catalyst.overwatch.schedule.model.Frequency;
import com.catalyst.overwatch.schedule.model.Notification;
import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.Respondent;
import com.catalyst.overwatch.schedule.model.Schedule;
import com.catalyst.overwatch.schedule.model.User;
import com.catalyst.overwatch.schedule.model.external.SurveyLink;
import com.catalyst.overwatch.schedule.repository.FlightRepository;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;

public class DailyJobTest {

  private static final LocalDate NOW = LocalDate.now();
  private static final LocalDate TOMORROW = NOW.plusDays(1);
  private static final LocalDate YESTERDAY = NOW.minusDays(1);
  private static final LocalDate TWO_WEEKS = NOW.plusWeeks(2);
  private static final String TEST_STRING = "test";
  private static final long TEST_LONG = 1;

  //Schedules for testing
  private Schedule testSchedule;
  private Schedule testOffSchedule;
  private Schedule testScheduleNullRespondent;
  private Schedule testScheduleNullEndDate;
  private Schedule testOneTimeSchedule;
  private Schedule testOneTimeOffSchedule;
  private Schedule testNoStartDateSchedule;
  private Schedule testScheduleExpiredEndDate;
  
  private List<Schedule> testSchedules = new ArrayList<Schedule>();
  private List<Schedule> testGoodSchedules = new ArrayList<Schedule>();
  private Set<Respondent> testRespondents = new HashSet<Respondent>();
  private List<Flight> testFlights = new ArrayList<Flight>();
  
  private Respondent testRespondent;
  private SurveyLink testSurveyLink;
  private Occurrence testOccurrence;
  private User testUser;
  private Flight testFlight;

  @InjectMocks
  private DailyJob testDailyJob = new DailyJob();

  @Mock
  private JobExecutionContext mockContext;

  @Mock
  private ScheduleRepository mockScheduleRepository;

  @Mock
  private OccurrenceRepository mockOccurrenceRepository;

  @Mock
  private FlightRepository mockFlightRepository;

  @Mock
  private RestTemplate mockRestTemplate;

  @Mock
  private Urls mockUrls;

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    setupSchedules();
    
    testUser = new User();
    testUser.setEmail(TEST_STRING);
    
    testRespondent = new Respondent();
    testRespondent.setUser(testUser);
    testRespondents.add(testRespondent);
    
    testSurveyLink = new SurveyLink(TEST_STRING, TEST_STRING);
    testSurveyLink.setSurveyDisplayLink(TEST_STRING);

    testOccurrence = new Occurrence();
    testOccurrence.setId(TEST_LONG);

    testFlight = new Flight();
    testFlight.setFlightNumber(TEST_LONG);

    testNoStartDateSchedule = new Schedule();
    testNoStartDateSchedule.setFrequency(Frequency.TWO_WEEKS);

    when(mockOccurrenceRepository.save(any(Occurrence.class))).thenReturn(testOccurrence);
    when(mockFlightRepository.findByScheduleId(anyLong())).thenReturn(testFlights);
    when(mockFlightRepository.findLargestByScheduleId(anyLong())).thenReturn(TEST_LONG);
    when(mockUrls.getNotificationEndpoint()).thenReturn(TEST_STRING);
    when(mockScheduleRepository.findAll()).thenReturn(testSchedules);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testExecuteHappyPathEmptyFlights() throws JobExecutionException {

    testDailyJob.execute(mockContext);

    verify(mockOccurrenceRepository, times(3)).save(any(Occurrence.class));
    verify(mockScheduleRepository, times(1)).findAll();
    verify(mockRestTemplate, times(3)).postForEntity(anyString(), any(SurveyLink.class), any());
    verify(mockFlightRepository, times(3)).findByScheduleId(anyLong());
    verify(mockFlightRepository, times(0)).findLargestByScheduleId(anyLong());
    verify(mockUrls, times(3)).getNotificationEndpoint();
  }

  @Test
  public void testExecuteHappyPath() throws JobExecutionException {
    testFlights.add(testFlight);
    testDailyJob.execute(mockContext);

    verify(mockOccurrenceRepository, times(3)).save(any(Occurrence.class));
    verify(mockScheduleRepository, times(1)).findAll();
    verify(mockRestTemplate, times(3)).postForEntity(anyString(), any(SurveyLink.class), any());
    verify(mockFlightRepository, times(3)).findByScheduleId(anyLong());
    verify(mockFlightRepository, times(3)).findLargestByScheduleId(anyLong());
    verify(mockUrls, times(3)).getNotificationEndpoint();
  }

  @Test
  public void testExecuteThrowsExceptionNullContext() throws JobExecutionException {
    exception.expect(NullPointerException.class);
    testDailyJob.execute(null);
  }

  @Test
  public void testGenerateOccurencesForTodayHappyPath() {
    testFlights.add(testFlight);
    testDailyJob.generateOccurrencesForToday(testGoodSchedules);

    verify(mockFlightRepository, times(3)).findByScheduleId(anyLong());
    verify(mockFlightRepository, times(3)).findLargestByScheduleId(anyLong());
    verify(mockOccurrenceRepository, times(2)).save(any(Occurrence.class));
    verify(mockFlightRepository, times(2)).save(any(Flight.class));
  }

  @Test
  public void testGenerateOccurencesForTodayEmptyFlights() {
    testDailyJob.generateOccurrencesForToday(testGoodSchedules);

    verify(mockFlightRepository, times(3)).findByScheduleId(anyLong());
    verify(mockFlightRepository, never()).findLargestByScheduleId(anyLong());
  }

  @Test
  public void testGenerateOccurrencesForTodayHappyPathEmptySchedules() {
    List<Schedule> testEmptySchedules = new ArrayList<Schedule>();

    testDailyJob.generateOccurrencesForToday(testEmptySchedules);

    verify(mockFlightRepository, never()).findByScheduleId(anyLong());
    verify(mockFlightRepository, never()).findByScheduleId(anyLong());
  }

  @Test
  public void testGenerateOccurrencesForTodayThrpowsExceptionNullSchedulesList() {
    exception.expect(NullPointerException.class);
    testDailyJob.generateOccurrencesForToday(null);
  }

  @Test
  public void testIsTodayOnScheduleFrequencyHappyPath() {

    boolean testTomorrow = testDailyJob.isTodayOnScheduleFrequency(testSchedule);
    boolean testYesterday = testDailyJob.isTodayOnScheduleFrequency(testOffSchedule);
    boolean testOneTime = testDailyJob.isTodayOnScheduleFrequency(testOneTimeSchedule);
    boolean testOneTimeNotProccessed =
        testDailyJob.isTodayOnScheduleFrequency(testOneTimeOffSchedule);


    assertTrue("Start Date of tomorrow should be true", testTomorrow);
    assertFalse("Start Date of yesterday should be false", testYesterday);
    assertTrue("One Time schedule should be true", testOneTime);
    assertFalse("One Time Off Schedule be false", testOneTimeNotProccessed);
  }

  @Test
  public void testIsTodayOnScheduleFrequencyThrowsExceptionNullSchedule() {
    exception.expect(NullPointerException.class);
    testDailyJob.isTodayOnScheduleFrequency(null);
  }

  @Test
  public void testIsTodayOnScheduleFrequencyThrowsExceptionNullStartDate() {
    exception.expect(NullPointerException.class);
    testDailyJob.isTodayOnScheduleFrequency(testNoStartDateSchedule);
  }

  @Test
  public void testIsTodayOnScheduleFrequencyThrowsExceptionNullFrequency() {
    exception.expect(NullPointerException.class);
    testSchedule.setFrequency(null);
    testDailyJob.isTodayOnScheduleFrequency(testSchedule);
  }

  @Test
  public void testGetSchedulesFromRepositoryAndProcess() {
    when(mockScheduleRepository.findAll()).thenReturn(testSchedules);
    List<Schedule> testSchedules = new ArrayList<Schedule>();
    testSchedules.addAll(testDailyJob.getSchedulesFromRepositoryAndProcess());

    assertEquals("testSchedules should contain 3 Schedules", 3, testSchedules.size());
  }

  @Test
  public void testBuildSurveyLinkHappyPath() {
    String testDailyJobResult = testDailyJob.buildSurveyLink(TEST_STRING, TEST_LONG);
    assertEquals("test", "null?suid=test&originatorId=1", testDailyJobResult);
  }

  @Test
  public void testBuildSurveyLinkNullSurveySuid() {
    exception.expect(NullPointerException.class);
    testDailyJob.buildSurveyLink(null, TEST_LONG);
  }

  @Test
  public void testBuildSurveyLinkNullOriginatorId() {
    exception.expect(NullPointerException.class);
    testDailyJob.buildSurveyLink(TEST_STRING, null);
  }

  @Test
  public void testGenerateNotificationHappyPath() {
    testDailyJob.generateNotification(TEST_STRING, TEST_STRING, TEST_STRING, TEST_STRING);
    verify(mockRestTemplate, times(1)).postForEntity(anyString(), any(Notification.class), any());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testGenerateNotificationThrowsOverwatchScheduleException() {
    exception.expect(OverwatchScheduleException.class);
    when(mockUrls.getNotificationEndpoint()).thenThrow(NullPointerException.class);
    testDailyJob.generateNotification(TEST_STRING, TEST_STRING, TEST_STRING, TEST_STRING);
  }

  @Test
  public void testGenerateNotificationNullEmailAddress() {
    exception.expect(NullPointerException.class);
    testDailyJob.generateNotification(null, TEST_STRING, TEST_STRING, TEST_STRING);
  }

  @Test
  public void testGenerateNotificationNullBody() {
    exception.expect(NullPointerException.class);
    testDailyJob.generateNotification(TEST_STRING, null, TEST_STRING, TEST_STRING);
  }

  @Test
  public void testGenerateNotificationNullSubject() {
    exception.expect(NullPointerException.class);
    testDailyJob.generateNotification(TEST_STRING, TEST_STRING, null, TEST_STRING);
  }

  @Test
  public void testGenerateNotificationNullErrorReference() {
    exception.expect(NullPointerException.class);
    testDailyJob.generateNotification(TEST_STRING, TEST_STRING, TEST_STRING, null);
  }

  /**
   * Setup method for Schedules.
   */
  private void setupSchedules() {
    
    // Happy Path Schedule
    testSchedule = new Schedule();
    testSchedule.setFrequency(Frequency.TWO_WEEKS);
    testSchedule.setStartDate(NOW);
    testSchedule.setEndDate(TOMORROW);
    testSchedule.setRespondents(testRespondents);
    testSchedule.setTemplateUri(TEST_STRING);
    testSchedule.setTemplateName(TEST_STRING);

    // One Time Schedule that should pass
    testOneTimeSchedule = new Schedule();
    testOneTimeSchedule.setFrequency(Frequency.ONE_TIME);
    testOneTimeSchedule.setStartDate(YESTERDAY);
    testOneTimeSchedule.setRespondents(testRespondents);
    testOneTimeSchedule.setTemplateUri(TEST_STRING);
    testOneTimeSchedule.setTemplateName(TEST_STRING);

    // Schedule that is not on to be processed today
    testOffSchedule = new Schedule();
    testOffSchedule.setFrequency(Frequency.TWO_WEEKS);
    testOffSchedule.setStartDate(YESTERDAY);
    testOffSchedule.setEndDate(TWO_WEEKS);
    testOffSchedule.setRespondents(testRespondents);

    // One Time Schedule that should not get processed
    testOneTimeOffSchedule = new Schedule();
    testOneTimeOffSchedule.setFrequency(Frequency.ONE_TIME);
    testOneTimeOffSchedule.setStartDate(NOW);
    testOneTimeOffSchedule.setRespondents(testRespondents);
    testOneTimeOffSchedule.setTemplateUri(TEST_STRING);
    testOneTimeOffSchedule.setTemplateName(TEST_STRING);

    // Schedule with no Respondent data
    testScheduleNullRespondent = new Schedule();
    testScheduleNullRespondent.setFrequency(Frequency.ONE_WEEK);
    testScheduleNullRespondent.setStartDate(NOW);
    testScheduleNullRespondent.setEndDate(TOMORROW);
    testScheduleNullRespondent.setTemplateUri(TEST_STRING);
    testScheduleNullRespondent.setTemplateName(TEST_STRING);

    // Schedule with end date of yesterday
    testScheduleExpiredEndDate = new Schedule();
    testScheduleExpiredEndDate.setFrequency(Frequency.ONE_WEEK);
    testScheduleExpiredEndDate.setStartDate(NOW);
    testScheduleExpiredEndDate.setEndDate(YESTERDAY);
    testScheduleExpiredEndDate.setRespondents(testRespondents);
    testScheduleExpiredEndDate.setTemplateUri(TEST_STRING);
    testScheduleExpiredEndDate.setTemplateName(TEST_STRING);

    // Schedule with no end date
    testScheduleNullEndDate = new Schedule();
    testScheduleNullEndDate.setFrequency(Frequency.ONE_WEEK);
    testScheduleNullEndDate.setStartDate(NOW);
    testScheduleNullEndDate.setRespondents(testRespondents);
    testScheduleNullEndDate.setTemplateUri(TEST_STRING);
    testScheduleNullEndDate.setTemplateName(TEST_STRING);

    testSchedules.add(testSchedule);
    testSchedules.add(testOneTimeSchedule);
    testSchedules.add(testOffSchedule);
    testSchedules.add(testScheduleNullRespondent);
    testSchedules.add(testScheduleNullEndDate);
    testSchedules.add(testScheduleExpiredEndDate);

    testGoodSchedules.add(testSchedule);
    testGoodSchedules.add(testOneTimeSchedule);
    testGoodSchedules.add(testScheduleNullRespondent);

  }

}

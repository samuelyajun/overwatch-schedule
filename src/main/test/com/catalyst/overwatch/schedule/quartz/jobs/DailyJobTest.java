package com.catalyst.overwatch.schedule.quartz.jobs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.catalyst.overwatch.schedule.constants.Urls;
import com.catalyst.overwatch.schedule.model.Frequency;
import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.Respondent;
import com.catalyst.overwatch.schedule.model.Schedule;
import com.catalyst.overwatch.schedule.model.User;
import com.catalyst.overwatch.schedule.model.external.SurveyLink;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;

public class DailyJobTest {

  private static final LocalDate NOW = LocalDate.now();
  private static final LocalDate TOMORROW = NOW.plusDays(1);
  private static final LocalDate YESTERDAY = NOW.minusDays(1);
  private static final LocalDate TWO_WEEKS = NOW.plusWeeks(2);
  private static final String TEST_STRING = "test";
  private static final long TEST_LONG = 1;

  private Schedule testSchedule;
  private Schedule testOffSchedule;
  private Schedule testScheduleNullRespondent;
  private Schedule testScheduleNullEndDate;
  private Schedule testScheduleExpiredEndDate;
  private List<Schedule> testSchedules = new ArrayList<Schedule>();
  private Respondent testRespondent;
  private Set<Respondent> testRespondents = new HashSet<Respondent>();
  private SurveyLink testSurveyLink;
  private Occurrence testOccurrence;
  private User testUser;
  
  @Spy
  private static Urls testUrls;

  @InjectMocks
  private DailyJob testDailyJob = new DailyJob();

  @Mock
  private JobExecutionContext mockContext;

  @Mock
  private ScheduleRepository mockScheduleRepository;

  @Mock
  private OccurrenceRepository mockOccurrenceRepository;

  @Mock
  private RestTemplate mockRestTemplate;
  
  @Mock
  private static Urls mockUrls;
  
  @Mock
  ResponseEntity<Object> mockResponseEntity;

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    testUser = new User();
    testUser.setEmail(TEST_STRING);
    testRespondent = new Respondent();
    testRespondent.setUser(testUser);
    testRespondents.add(testRespondent);

    setupSchedules();

    testSurveyLink = new SurveyLink(TEST_STRING, TEST_STRING);
    testSurveyLink.setSurveyDisplayLink(TEST_STRING);

    testOccurrence = new Occurrence();
    testOccurrence.setId(TEST_LONG);
    
    testUrls = new Urls();

    when(mockRestTemplate.postForEntity(anyString(), any(SurveyLink.class), any()))
        .thenReturn(mockResponseEntity);
    when(mockResponseEntity.getBody()).thenReturn(testSurveyLink);
    when(mockOccurrenceRepository.save(any(Occurrence.class))).thenReturn(testOccurrence);
    //when(mockUrls.getInstance()).thenReturn(mockUrls);
    when(mockUrls.getNotificationEndpoint()).thenReturn(TEST_STRING);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testExecuteHappyPath() throws JobExecutionException {
    when(mockScheduleRepository.findAll()).thenReturn(testSchedules);
    testDailyJob.execute(mockContext);

    verify(mockScheduleRepository, times(1)).findAll();
    verify(mockRestTemplate, times(2)).postForEntity(anyString(), any(SurveyLink.class), any());
    verify(mockResponseEntity, times(2)).getBody();
  }

  @Test
  public void testExecuteThrowsExceptionNullContext() throws JobExecutionException {
    exception.expect(NullPointerException.class);
    testDailyJob.execute(null);
  }

  @Test
  public void testGenerateOccurrencesForTodayHappyPath() {
    List<Schedule> testEmptySchedules = new ArrayList<Schedule>();

    testDailyJob.generateOccurrencesForToday(testEmptySchedules);

    verify(mockRestTemplate, never()).postForEntity(anyString(), any(SurveyLink.class), any());
    verify(mockResponseEntity, never()).getBody();

    testDailyJob.generateOccurrencesForToday(testSchedules);

    verify(mockRestTemplate, times(5)).postForEntity(anyString(), any(SurveyLink.class), any());
    verify(mockResponseEntity, times(5)).getBody();
  }

  // @Test(expected = NullPointerException.class)
  // public void testGenerateOccurrencesForTodayThrpowsExceptionNullSchedulesList() {
  // testDailyJob.generateOccurrencesForToday(null);
  // }

  @Test
  public void testIsTodayOnScheduleFrequencyHappyPath() {

    boolean testTomorrow = testDailyJob.isTodayOnScheduleFrequency(testSchedule);
    testSchedule.setStartDate(YESTERDAY);
    boolean testYesterday = testDailyJob.isTodayOnScheduleFrequency(testSchedule);
    testSchedule.setFrequency(Frequency.ONE_TIME);
    boolean testOneTime = testDailyJob.isTodayOnScheduleFrequency(testSchedule);


    assertTrue("Start Date of tomorrow should be true", testTomorrow);
    assertFalse("Start Date of yesterday should be false", testYesterday);
    assertTrue("One shotschedule should trump all", testOneTime);
  }

  // @Test(expected = NullPointerException.class)
  // public void testIsTodayOnScheduleFrequencyThrowsExceptionNullSchedule() {
  // @SuppressWarnings("unused")
  // boolean testNullSchedule = testDailyJob.isTodayOnScheduleFrequency(null);
  // }
  //
  // @Test(expected = NullPointerException.class)
  // public void testIsTodayOnScheduleFrequencyThrowsExceptionNullStartDate() {
  // testSchedule.setStartDate(null);
  // @SuppressWarnings("unused")
  // boolean testNullStartDate = testDailyJob.isTodayOnScheduleFrequency(testSchedule);
  // }
  //
  // @Test(expected = NullPointerException.class)
  // public void testIsTodayOnScheduleFrequencyThrowsExceptionNullFrequency() {
  // testSchedule.setFrequency(null);
  // @SuppressWarnings("unused")
  // boolean testNullFrequency = testDailyJob.isTodayOnScheduleFrequency(testSchedule);
  // }

  private void setupSchedules() {
    // Happy Path Schedule
    testSchedule = new Schedule();
    testSchedule.setFrequency(Frequency.TWO_WEEKS);
    testSchedule.setStartDate(NOW);
    testSchedule.setEndDate(TOMORROW);
    testSchedule.setRespondents(testRespondents);
    testSchedule.setTemplateUri(TEST_STRING);
    testSchedule.setTemplateName(TEST_STRING);

    // Schedule that is not on to be processed today
    testOffSchedule = new Schedule();
    testOffSchedule.setFrequency(Frequency.TWO_WEEKS);
    testOffSchedule.setStartDate(YESTERDAY);
    testOffSchedule.setEndDate(TWO_WEEKS);
    testOffSchedule.setRespondents(testRespondents);
    testOffSchedule.setTemplateUri(TEST_STRING);
    testOffSchedule.setTemplateName(TEST_STRING);

    // Schedule with no Respondent data
    testScheduleNullRespondent = new Schedule();
    testScheduleNullRespondent.setFrequency(Frequency.ONE_WEEK);
    testScheduleNullRespondent.setStartDate(NOW);
    testScheduleNullRespondent.setEndDate(TOMORROW);
    testScheduleNullRespondent.setTemplateUri(TEST_STRING);
    testScheduleNullRespondent.setTemplateName(TEST_STRING);

    // Schedule with no end date
    testScheduleNullEndDate = new Schedule();
    testScheduleNullEndDate.setFrequency(Frequency.ONE_WEEK);
    testScheduleNullEndDate.setStartDate(NOW);
    testScheduleNullEndDate.setRespondents(testRespondents);
    testScheduleNullEndDate.setTemplateUri(TEST_STRING);
    testScheduleNullEndDate.setTemplateName(TEST_STRING);

    // Schedule with end date of yesterday
    testScheduleExpiredEndDate = new Schedule();
    testScheduleExpiredEndDate.setFrequency(Frequency.ONE_WEEK);
    testScheduleExpiredEndDate.setStartDate(NOW);
    testScheduleExpiredEndDate.setEndDate(YESTERDAY);
    testScheduleExpiredEndDate.setRespondents(testRespondents);
    testScheduleExpiredEndDate.setTemplateUri(TEST_STRING);
    testScheduleExpiredEndDate.setTemplateName(TEST_STRING);

    testSchedules.add(testSchedule);
    testSchedules.add(testOffSchedule);
    testSchedules.add(testScheduleNullRespondent);
    testSchedules.add(testScheduleNullEndDate);
    testSchedules.add(testScheduleExpiredEndDate);
  }

}

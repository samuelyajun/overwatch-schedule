package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.constants.Urls;
import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.Respondent;
import com.catalyst.overwatch.schedule.model.Schedule;
import com.catalyst.overwatch.schedule.repository.FlightRepository;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import com.catalyst.overwatch.schedule.repository.ScheduleRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.runners.MockitoJUnitRunner;
import org.quartz.JobExecutionContext;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Mock
    private RestTemplate mockRestTemplate;

    @Mock
    private OccurrenceRepository mockOccurrenceRepository;

    @Mock
    private ScheduleRepository mockScheduleRepository;

    @Mock
    private FlightRepository mockFlightRepository;

    @Mock
    private Urls mockUrls;

    @Mock
    private Schedule schedule;

    @Mock
    private Occurrence mockOccurrence;

    @Mock
    private LocalDate localDate;

    @Mock
    private Respondent mockRespondent;

    private List<Occurrence> testOccurrences = new ArrayList<Occurrence>();

    @Test
    public void buildTattleBodyTest() {
        mockOccurrence.setFlightNumber(1);
        mockOccurrence.setScheduleId(1);
        mockOccurrence.setGenerationDate(localDate);
        mockOccurrence.setScheduleId(1);



        when(mockScheduleRepository.findById(1)).thenReturn(schedule);

        String expected = testTattlesJob.buildTattleBody(List<mockOccurrence>);

    }
}

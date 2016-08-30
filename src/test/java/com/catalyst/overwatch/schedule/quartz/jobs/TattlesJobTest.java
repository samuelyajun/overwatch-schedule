package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.constants.NotificationConstants;
import com.catalyst.overwatch.schedule.constants.Urls;
import com.catalyst.overwatch.schedule.model.Flight;
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

public class TattlesJobTest {

    @InjectMocks
    private TattlesJob testTattlesJob;

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

    @Mock
    private Schedule schedule;

    @Mock
    private Flight flight;

    @Mock
    private Occurrence mockOccurrence;

    @Mock
    private LocalDate localDate;

    @Mock
    private Respondent mockRespondent;

    private List<Occurrence> testOccurrences = new ArrayList<Occurrence>();

    @Test
    public void flightIsNotNullTest() {
        when(mockFlightRepository.findByScheduleId(1)).thenReturn((List<Flight>) flight);

    }
}

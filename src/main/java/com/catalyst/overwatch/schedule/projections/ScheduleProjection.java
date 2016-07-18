package com.catalyst.overwatch.schedule.projections;

import com.catalyst.overwatch.schedule.model.Schedule;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "scheduleDetails", types = {Schedule.class})
public interface ScheduleProjection{
}

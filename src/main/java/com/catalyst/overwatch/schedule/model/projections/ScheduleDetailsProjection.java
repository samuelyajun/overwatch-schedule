package com.catalyst.overwatch.schedule.model.projections;

import com.catalyst.overwatch.schedule.model.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Set;

@Projection(name = "scheduleDetails", types = {Schedule.class})
public interface ScheduleDetailsProjection {

    Long getId();

    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate getStartDate();

    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate getEndDate();

    Frequency getFrequency();

    String getInterval();

    Set<RespondentDetailsProjection> getRespondents();
}

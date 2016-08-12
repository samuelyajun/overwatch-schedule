package com.catalyst.overwatch.schedule.model.projections;

import com.catalyst.overwatch.schedule.model.Frequency;
import com.catalyst.overwatch.schedule.model.Schedule;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.Set;

/**
 * A schedule projection that exposes Respondent details for consumption.
 *
 * @author hmccardell
 */
@Projection(name = "scheduleDetails", types = {Schedule.class})
public interface ScheduleDetailsProjection {

    Long getId();

    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate getStartDate();

    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate getEndDate();

    Frequency getFrequency();

    String getTemplateUri();

    String getTemplateName();

    Set<RespondentDetailsProjection> getRespondents();
}

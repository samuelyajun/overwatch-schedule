package com.catalyst.overwatch.schedule.model.projections;

import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.Respondent;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

/**
 * This projection provides details on occurrences, and is used to fill in respondent details
 * needed for front end consumption that aren't exposed on a normal "GET" request to the repository.
 *
 * @author hmccardell
 */
@Projection(name = "occurrenceDetails", types = {Occurrence.class})
public interface OccurrenceDetailsProjection {

  long getId();

  @JsonSerialize(using = LocalDateSerializer.class)
  LocalDate getGenerationDate();

  boolean isComplete();

  RespondentDetailsProjection getRespondent();

}


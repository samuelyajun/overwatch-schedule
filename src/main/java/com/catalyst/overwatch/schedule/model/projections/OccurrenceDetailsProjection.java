package com.catalyst.overwatch.schedule.model.projections;

import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.model.Respondent;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

/**
 * Created by hmccardell on 8/4/2016.
 */
@Projection(name = "occurrenceDetails", types = {Occurrence.class})
public interface OccurrenceDetailsProjection {

  long getId();
  RespondentDetailsProjection getRespondent();
  LocalDate getGenerationDate();
  boolean isComplete();

}


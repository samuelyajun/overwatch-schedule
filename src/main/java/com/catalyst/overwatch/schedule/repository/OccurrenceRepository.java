package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by hmccardell on 7/28/2016.
 */
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

  Occurrence findById(@Param(value = "id") long id);

  List<Occurrence> findByGenerationDateAndIsComplete(@Param(value = "generationDate") LocalDate date,
                                                     @Param(value = "isComplete") boolean isComplete);

}

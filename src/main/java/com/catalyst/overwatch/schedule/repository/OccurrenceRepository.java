package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Exposes a basic RESTFUL endpoint for Occurences.
 * 
 * @author hmccardell
 * @author bfutral
 */
@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

  Occurrence findById(@Param(value = "id") long id);

  List<Occurrence> findByGenerationDateLessThanEqualAndIsComplete(@Param(value = "generationDate") LocalDate date,
                                                     @Param(value = "isComplete") boolean isComplete);

}

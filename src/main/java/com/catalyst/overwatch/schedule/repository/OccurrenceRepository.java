package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * This repository extends JpaRepository, and by its existence exposes basic CRUD endpoints for
 * occurrences.  In addition, the below queries are exposed for consumption in Java and via
 * /search endpoints in the API.
 *
 * example: http://localhost:8090/occurrences/search/findById?id=1
 *
 * @author hmccardell
 */
@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

  Occurrence findById(@Param(value = "id") long id);

  List<Occurrence> findByGenerationDateLessThanEqualAndIsComplete(@Param(value = "generationDate") LocalDate date,
                                                     @Param(value = "isComplete") boolean isComplete);

  List<Occurrence> findByScheduleIdOrderByGenerationDateAsc(@Param(value = "scheduleId") long scheduleId);

  List<Occurrence> findByScheduleIdAndFlightNumber(@Param(value = "scheduleId") long scheduleId,
                                                       @Param(value = "flightNumber") long flightNumber);
}

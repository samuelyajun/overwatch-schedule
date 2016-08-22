package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * An internal repository that helps track schedules and existing flights of occurrences.
 * This resource is not exported for consumption from the API.
 *
 * @author hmccardell
 */
@RestResource(exported = false)
public interface FlightRepository extends JpaRepository<Flight, Long> {

  @Query("select max(f.flightNumber) from Flight f where scheduleId = :scheduleId ")
  long findLargestByScheduleId(@Param(value = "scheduleId") long scheduleId);

  List<Flight> findByScheduleId(@Param(value = "scheduleId") long scheduleId);

  List<Flight> findByScheduleIsActiveAndIsClosed(@Param(value = "scheduleIsActive") boolean scheduleIsActive,
                                                 @Param(value = "isClosed") boolean isClosed);
}

package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by hmccardell on 8/11/2016.
 */
public interface FlightRepository extends JpaRepository<Flight, Long> {

  @RestResource(exported = false)
  List<Flight> findByScheduleIsActiveAndIsClosed(@Param(value = "scheduleIsActive") boolean scheduleIsActive,
                                           @Param(value = "isClosed") boolean isClosed);
}

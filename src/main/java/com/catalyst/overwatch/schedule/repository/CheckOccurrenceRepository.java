package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.CheckOccurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by hmccardell on 8/11/2016.
 */
public interface CheckOccurrenceRepository extends JpaRepository<CheckOccurrence, Long> {

  @RestResource(exported = false)
  List<CheckOccurrence> findByScheduleIdAndIsClosed(@Param(value = "scheduleId") long scheduleId,
                                                            @Param(value = "isClosed") boolean isClosed);
}

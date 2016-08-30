package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Exposes a basic RESTFUL endpoint for Schedules.
 * 
 * @author bpyl
 * @author bfutral
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

  List<Schedule> findAllByStartDate(LocalDate startDate);

  Schedule findByRespondentsId(@Param(value = "respondent_id") long id);

  List<Schedule> findByIsActive(boolean isActive);

  Schedule findById(Long scheduleId);
}

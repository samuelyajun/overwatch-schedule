package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bpyl on 6/14/2016.
 */

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hmccardell on 7/22/2016.
 */
@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {
}

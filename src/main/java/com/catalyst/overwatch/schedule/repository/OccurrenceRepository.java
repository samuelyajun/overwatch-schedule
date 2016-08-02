package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hmccardell on 7/28/2016.
 */
@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

  Occurrence findById(@Param(value = "id") long id);

  List<Occurrence> findAllByGenerationDate(@Param(value = "generationDate") LocalDateTime date);

}

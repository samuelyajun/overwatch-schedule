package com.catalyst.overwatch.schedule.repository;

import com.catalyst.overwatch.schedule.model.Respondent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespondentRepository extends JpaRepository<Respondent, Long> {

}

package com.catalyst.overwatch.schedule.quartz.jobs;

import com.catalyst.overwatch.schedule.model.Occurrence;
import com.catalyst.overwatch.schedule.repository.OccurrenceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmccardell on 7/28/2016.
 */
public class NagsJob implements Job {

  @Autowired
  private OccurrenceRepository occurrenceRepository;

  Logger logger = LogManager.getRootLogger();

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    logger.info("Nags job");

    LocalDateTime todaysDate = LocalDateTime.now();
    List<Occurrence> listOfOccurrences = new ArrayList<>();
    listOfOccurrences.addAll(occurrenceRepository.findByGenerationDateAndIsComplete(todaysDate, false));

    logger.info("List of occurrences:");
    logger.info(listOfOccurrences.toString());

  }
}

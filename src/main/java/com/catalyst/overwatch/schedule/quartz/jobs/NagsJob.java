package com.catalyst.overwatch.schedule.quartz.jobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by hmccardell on 7/28/2016.
 */
public class NagsJob implements Job {

    Logger logger = LogManager.getRootLogger();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("Nags job");

    }
}

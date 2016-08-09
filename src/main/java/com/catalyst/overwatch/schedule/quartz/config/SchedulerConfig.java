package com.catalyst.overwatch.schedule.quartz.config;

import com.catalyst.overwatch.schedule.quartz.jobs.DailyJob;
import com.catalyst.overwatch.schedule.quartz.jobs.NagsJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * This class contains majority of the Schedule service's Quartz configuration.  Job details,
 * triggers and factory beans are described below.
 * 
 * See the quartz.jobs package for the actual tasks each job will execute.
 *
 * @author hmccardell
 */
@Configuration
public class SchedulerConfig {

  /**
   * A JobFactory is responsible for producing instances of <code>Job</code>
   * classes.
   *
   * @param applicationContext the application's context.
   * @return a job factory which supports @Autowired services for job execution.
   */
  @Bean
  public JobFactory jobFactory(ApplicationContext applicationContext) {
    AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
    jobFactory.setApplicationContext(applicationContext);
    return jobFactory;
  }

  /**
   * Sets the resource path for quartz properties into a bean and returns it.
   *
   * @return a properties bean.
   * @throws IOException
   */
  @Bean
  public Properties quartzProperties() throws IOException {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }

  /**
   * A Spring {@link FactoryBean} for creating a Quartz {@link org.quartz.JobDetail}
   * instance, supporting bean-style usage for JobDetail configuration.
   *
   * @param jobClass implementation class of the job.
   * @return a job detail factory bean with a set job class and durability.
   */
  public static JobDetailFactoryBean createJobDetail(Class jobClass) {
    JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
    factoryBean.setJobClass(jobClass);
    factoryBean.setDurability(true);
    return factoryBean;
  }

  /**
   * A Spring {@link FactoryBean} for creating a Quartz {@link org.quartz.CronTrigger}
   * instance, supporting bean-style usage for trigger configuration.
   *
   * @param jobDetail      Conveys the detail properties of a given <code>Job</code> instance. JobDetails are
   *                       to be created/defined with {@link JobBuilder}.
   * @param beanName       The name of the job.
   * @param group          An identifier for grouping jobs together.
   * @param cronExpression The cron expression that determines when this job will be executed.
   * @return a cron trigger factory bean set with all the details of the job.
   */
  public static CronTriggerFactoryBean createTrigger(JobDetail jobDetail, String beanName, String group, String cronExpression) {
    CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
    factoryBean.setBeanName(beanName);
    factoryBean.setGroup(group);
    factoryBean.setCronExpression(cronExpression);
    factoryBean.setJobDetail(jobDetail);

    return factoryBean;
  }

  /**
   * A Spring {@link FactoryBean} that creates and configures a Quartz {@link org.quartz.Scheduler},
   * manages its lifecycle as part of the Spring application context, and exposes the
   * Scheduler as bean reference for dependency injection.
   *
   * @param jobFactory      responsible for producing an instance of the Job class.
   * @param dailyJobTrigger the trigger for the Daily Job, which creates occurrences for schedules and calls
   *                        the notifications service to generate emails to users.
   * @return a scheduler factory bean set with the job factory and the triggers to execute.
   * @throws IOException
   */
  @Bean
  public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory,
                                                   @Qualifier("dailyJobTrigger") Trigger dailyJobTrigger,
                                                   @Qualifier("nagsJobTrigger") Trigger nagsJobTrigger
  ) throws IOException {
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setJobFactory(jobFactory);
    factory.setQuartzProperties(quartzProperties());
    factory.setTriggers(dailyJobTrigger, nagsJobTrigger);

    return factory;
  }

  @Bean(name = "dailyJobTrigger")
  public CronTriggerFactoryBean dailyJobTrigger(@Qualifier("dailyJobDetail") JobDetail jobDetail) {
    String beanName = "Daily Process";
    String group = "Daily";
<<<<<<< HEAD
    String cronExpression = "0/20 * * * * ? *";  //8:00 AM EST, every day
=======
    String cronExpression = SchedulerConstants.EIGHT_AM_EASTERN_EVERY_DAY;
>>>>>>> bd9ba6c... made constants for the cron schedules
    return createTrigger(jobDetail, beanName, group, cronExpression);
  }

  @Bean(name = "nagsJobTrigger")
  public CronTriggerFactoryBean nagsJobTrigger(@Qualifier("nagsJobDetail") JobDetail jobDetail) {
    String beanName = "Nags Process";
    String group = "Nags";
    String cronExpression = SchedulerConstants.FOUR_THIRTY_PM_EASTERN_EVERY_DAY;
    return createTrigger(jobDetail, beanName, group, cronExpression);
  }

<<<<<<< HEAD
=======
  @Bean(name = "tattlesJobTrigger")
  public CronTriggerFactoryBean tattlesJobTrigger(@Qualifier("tattlesJobDetail") JobDetail jobDetail) {
    String beanName = "Tattles Process";
    String group = "Tattles";
    String cronExpression = SchedulerConstants.JUST_AFTER_MIDNIGHT_EVERY_DAY;
    return createTrigger(jobDetail, beanName, group, cronExpression);
  }

  @Bean
  public JobDetailFactoryBean tattlesJobDetail() {
    return createJobDetail(TattlesJob.class);
  }

>>>>>>> bd9ba6c... made constants for the cron schedules
  @Bean
  public JobDetailFactoryBean dailyJobDetail() {
    return createJobDetail(DailyJob.class);
  }

  @Bean
  public JobDetailFactoryBean nagsJobDetail() {
    return createJobDetail(NagsJob.class);
  }
}

package com.catalyst.overwatch.schedule.quartz.config;

import com.catalyst.overwatch.schedule.quartz.jobs.DailyJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
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
 * Created by hmccardell on 7/25/2016.
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory,
                                                     @Qualifier("dailyJobTrigger") Trigger dailyJobTrigger) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        factory.setTriggers(dailyJobTrigger);

        return factory;
    }

    @Bean(name = "dailyJobTrigger")
    public CronTriggerFactoryBean dailyJobTrigger(@Qualifier("dailyJobDetail") JobDetail jobDetail){
        String beanName = "Daily Process";
        String group = "Group 1";
        String cronExpression = "0/20 * * * * ?";
        return createTrigger(jobDetail, beanName, group, cronExpression);
    }

    private static JobDetailFactoryBean createJobDetail(Class jobClass) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        return factoryBean;
    }

    private static CronTriggerFactoryBean createTrigger(JobDetail jobDetail, String beanName, String group, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setBeanName(beanName);
        factoryBean.setGroup(group);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setJobDetail(jobDetail);

        return factoryBean;
    }

    @Bean
    public JobDetailFactoryBean dailyJobDetail() {
        return createJobDetail(DailyJob.class);
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();

    }
}

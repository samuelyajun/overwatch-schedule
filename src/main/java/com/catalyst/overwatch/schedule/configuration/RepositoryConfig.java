package com.catalyst.overwatch.schedule.configuration;

import com.catalyst.overwatch.schedule.model.Schedule;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by hmccardell on 7/18/2016.
 */
@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter{

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Schedule.class);
    }
}

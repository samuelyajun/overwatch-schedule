package com.catalyst.overwatch.schedule.configuration;

import com.catalyst.overwatch.schedule.model.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(Schedule.class);
    config.exposeIdsFor(User.class);
    config.exposeIdsFor(Respondent.class);
    config.exposeIdsFor(Occurrence.class);
    config.exposeIdsFor(AllowedAttribute.class);
  }
}


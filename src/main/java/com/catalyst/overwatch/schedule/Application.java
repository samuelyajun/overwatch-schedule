package com.catalyst.overwatch.schedule;

import com.catalyst.overwatch.schedule.constants.Urls;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;

/**
 * Created by bpyl on 6/14/2016.
 */
@SpringBootApplication
public class Application {


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
    b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    return b;
  }
}


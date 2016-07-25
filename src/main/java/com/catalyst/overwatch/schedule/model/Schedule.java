package com.catalyst.overwatch.schedule.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by bpyl on 6/14/2016.
 */
@Entity
@Audited
public class Schedule implements Serializable {
  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @NotNull(message = "Start date cannot be null")
  @Column(name = "start_date")
  private LocalDate startDate;

  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @Column(name = "end_date")
  private LocalDate endDate;

  @Column
  @Enumerated(EnumType.STRING)
  private Frequency frequency;

  @Column
  private String interval;

  @Column
  private String survey;

  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "schedule_id")
  private Set<Respondent> respondents;  

  public Schedule() {

  };

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Frequency getFrequency() {
    return frequency;
  }

  public void setFrequency(Frequency frequency) {
    this.frequency = frequency;
  }

  public String getSurvey() {
    return survey;
  }

  public void setSurvey(String survey) {
    this.survey = survey;
  }

  public String getInterval() {
    return interval;
  }

  public void setInterval(String interval) {
    this.interval = interval;
  }

  public Set<Respondent> getRespondents() {
    return respondents;
  }

  public void setRespondents(Set<Respondent> respondents) {
    this.respondents = respondents;
  }
}

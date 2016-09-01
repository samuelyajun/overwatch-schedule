package com.catalyst.overwatch.schedule.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.google.common.base.Objects;
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

  @Enumerated(EnumType.STRING)
  private Frequency frequency;

  @Column(name = "template_uri")
  private String templateUri;

  @Column(name = "template_name")
  private String templateName;

  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @JoinColumn(name = "schedule_id")
  private Set<Respondent> respondents;

  private boolean isActive;

  public Schedule() {
  }

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

  public Set<Respondent> getRespondents() {
    return respondents;
  }

  public void setRespondents(Set<Respondent> respondents) {
    this.respondents = respondents;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public String getTemplateUri() {
    return templateUri;
  }

  public void setTemplateUri(String templateUri) {
    this.templateUri = templateUri;
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  @Override
  public String toString() {
    return "Schedule{" +
            "id=" + id +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", frequency=" + frequency +
            ", templateUri='" + templateUri + '\'' +
            ", templateName='" + templateName + '\'' +
            ", respondents=" + respondents +
            ", isActive=" + isActive +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Schedule schedule = (Schedule) o;
    return isActive == schedule.isActive &&
            Objects.equal(startDate, schedule.startDate) &&
            Objects.equal(endDate, schedule.endDate) &&
            frequency == schedule.frequency &&
            Objects.equal(templateUri, schedule.templateUri) &&
            Objects.equal(templateName, schedule.templateName) &&
            Objects.equal(respondents, schedule.respondents);
  }

}

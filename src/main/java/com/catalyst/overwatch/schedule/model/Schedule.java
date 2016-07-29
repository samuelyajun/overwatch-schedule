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

  @Enumerated(EnumType.STRING)
  private Frequency frequency;

  private String interval;

  @Column(name = "template_uri")
  private String templateuri;

  @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinColumn(name = "schedule_id")
  private Set<Respondent> respondents;

  private Boolean isActive;

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

  public String getInterval() {
    return interval;
  }

  public void setInterval(String interval) {
    this.interval = interval;
  }

  public String getTemplateURI() {
    return templateuri;
  }

  public void setTemplateURI(String templateURI) {
    this.templateuri = templateURI;
  }

  public Set<Respondent> getRespondents() {
    return respondents;
  }

  public void setRespondents(Set<Respondent> respondents) {
    this.respondents = respondents;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  @Override
  public String toString() {
    return "Schedule{" +
            "id=" + id +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", frequency=" + frequency +
            ", interval='" + interval + '\'' +
            ", templateURI=" + templateuri +
            ", respondents=" + respondents +
            ", isActive=" + isActive +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Schedule schedule = (Schedule) o;

    if (!startDate.equals(schedule.startDate)) return false;
    if (endDate != null ? !endDate.equals(schedule.endDate) : schedule.endDate != null) return false;
    if (frequency != schedule.frequency) return false;
    if (!interval.equals(schedule.interval)) return false;
    if (!templateuri.equals(schedule.templateuri)) return false;
    if (!respondents.equals(schedule.respondents)) return false;
    return isActive.equals(schedule.isActive);

  }

  @Override
  public int hashCode() {
    int result = startDate.hashCode();
    result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
    result = 31 * result + frequency.hashCode();
    result = 31 * result + interval.hashCode();
    result = 31 * result + templateuri.hashCode();
    result = 31 * result + respondents.hashCode();
    result = 31 * result + isActive.hashCode();
    return result;
  }
}

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

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(final long id) {
    this.id = id;
  }

  /**
   * @return the startDate
   */
  public LocalDate getStartDate() {
    return startDate;
  }

  /**
   * @param startDate the startDate to set
   */
  public void setStartDate(final LocalDate startDate) {
    this.startDate = startDate;
  }

  /**
   * @return the endDate
   */
  public LocalDate getEndDate() {
    return endDate;
  }

  /**
   * @param endDate the endDate to set
   */
  public void setEndDate(final LocalDate endDate) {
    this.endDate = endDate;
  }

  /**
   * @return the frequency
   */
  public Frequency getFrequency() {
    return frequency;
  }

  /**
   * @param frequency the frequency to set
   */
  public void setFrequency(final Frequency frequency) {
    this.frequency = frequency;
  }

  /**
   * @return the templateUri
   */
  public String getTemplateUri() {
    return templateUri;
  }

  /**
   * @param templateUri the templateUri to set
   */
  public void setTemplateUri(final String templateUri) {
    this.templateUri = templateUri;
  }

  /**
   * @return the templateName
   */
  public String getTemplateName() {
    return templateName;
  }

  /**
   * @param templateName the templateName to set
   */
  public void setTemplateName(final String templateName) {
    this.templateName = templateName;
  }

  /**
   * @return the respondents
   */
  public Set<Respondent> getRespondents() {
    return respondents;
  }
  
  /**
   * @param respondents the respondents to set
   */
  public void setRespondents(final Set<Respondent> respondents) {
    this.respondents = respondents;
  }

  /**
   * @return the isActive
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * @param isActive the isActive to set
   */
  public void setActive(final boolean isActive) {
    this.isActive = isActive;
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

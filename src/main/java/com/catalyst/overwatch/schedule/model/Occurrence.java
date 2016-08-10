package com.catalyst.overwatch.schedule.model;

import com.google.common.base.Objects;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * An occurrence is generated for each respondent on a schedule every time that schedule's frequency comes around.
 * For example, if the frequency is weekly and there are three respondents, then three occurrences will be
 * generated every week. This means that if a respondent has not answered a survey, they may have multiple
 * occurrences active for the same schedule simultaneously.
 *
 * @author hmccardell
 */
@Entity
@Audited
public class Occurrence implements Serializable {

  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(cascade = {CascadeType.MERGE})
  private Respondent respondent;

  @Column
  private LocalDate generationDate;

  @Column(name = "is_complete")
  private boolean isComplete;

  private long scheduleId;

  public Occurrence() {
  }

  public Occurrence(Respondent respondent, long scheduleId) {
    isComplete = false;
    this.respondent = respondent;
    this.generationDate = LocalDate.now();
    this.scheduleId = scheduleId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Respondent getRespondent() {
    return respondent;
  }

  public void setRespondent(Respondent respondent) {
    this.respondent = respondent;
  }

  public LocalDate getGenerationDate() {
    return generationDate;
  }

  public void setGenerationDate(LocalDate generationDate) {
    this.generationDate = generationDate;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public void setComplete(boolean isComplete) {
    this.isComplete = isComplete;
  }

  public long getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(long scheduleId) {
    this.scheduleId = scheduleId;
  }

  @Override
  public String toString() {
    return "Occurrence{" +
            "id=" + id +
            ", respondent=" + respondent +
            ", generationDate=" + generationDate +
            ", isComplete=" + isComplete +
            ", scheduleId=" + scheduleId +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Occurrence that = (Occurrence) o;
    return id == that.id &&
            isComplete == that.isComplete &&
            scheduleId == that.scheduleId &&
            Objects.equal(respondent, that.respondent) &&
            Objects.equal(generationDate, that.generationDate);
  }

}

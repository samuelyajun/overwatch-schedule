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

  @Column(name = "generation_date")
  private LocalDate generationDate;

  @Column(name = "is_complete")
  private boolean isComplete;

  @Column(name = "schedule_id")
  private long scheduleId;

  @Column(name = "flight_number")
  private long flightNumber;

  public Occurrence() {
  }

  public Occurrence(Respondent respondent, long scheduleId, long flightNumber) {
    isComplete = false;
    this.respondent = respondent;
    this.generationDate = LocalDate.now();
    this.scheduleId = scheduleId;
    this.flightNumber = flightNumber;
  }

  /**
   * Sets new scheduleId.
   *
   * @param scheduleId New value of scheduleId.
   */
  public void setScheduleId(long scheduleId) {
    this.scheduleId = scheduleId;
  }

  /**
   * Gets id.
   *
   * @return Value of id.
   */
  public long getId() {
    return id;
  }

  /**
   * Gets serialVersionUID.
   *
   * @return Value of serialVersionUID.
   */
  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  /**
   * Sets new isComplete.
   *
   * @param isComplete New value of isComplete.
   */
  public void setIsComplete(boolean isComplete) {
    this.isComplete = isComplete;
  }

  /**
   * Sets new flightNumber.
   *
   * @param flightNumber New value of flightNumber.
   */
  public void setFlightNumber(long flightNumber) {
    this.flightNumber = flightNumber;
  }

  /**
   * Sets new id.
   *
   * @param id New value of id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets flightNumber.
   *
   * @return Value of flightNumber.
   */
  public long getFlightNumber() {
    return flightNumber;
  }

  /**
   * Sets new generationDate.
   *
   * @param generationDate New value of generationDate.
   */
  public void setGenerationDate(LocalDate generationDate) {
    this.generationDate = generationDate;
  }

  /**
   * Gets scheduleId.
   *
   * @return Value of scheduleId.
   */
  public long getScheduleId() {
    return scheduleId;
  }

  /**
   * Gets respondent.
   *
   * @return Value of respondent.
   */
  public Respondent getRespondent() {
    return respondent;
  }

  /**
   * Gets generationDate.
   *
   * @return Value of generationDate.
   */
  public LocalDate getGenerationDate() {
    return generationDate;
  }

  /**
   * Gets isComplete.
   *
   * @return Value of isComplete.
   */
  public boolean getIsComplete() {
    return isComplete;
  }

  /**
   * Sets new respondent.
   *
   * @param respondent New value of respondent.
   */
  public void setRespondent(Respondent respondent) {
    this.respondent = respondent;
  }

  @Override
  public String toString() {
    return "Occurrence{" +
            "id=" + id +
            ", respondent=" + respondent +
            ", generationDate=" + generationDate +
            ", isComplete=" + isComplete +
            ", scheduleId=" + scheduleId +
            ", flightNumber=" + flightNumber +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Occurrence that = (Occurrence) o;
    return isComplete == that.isComplete &&
            scheduleId == that.scheduleId &&
            flightNumber == that.flightNumber &&
            Objects.equal(respondent, that.respondent) &&
            Objects.equal(generationDate, that.generationDate);
  }

}

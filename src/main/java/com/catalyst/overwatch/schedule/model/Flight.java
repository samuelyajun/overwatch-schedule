package com.catalyst.overwatch.schedule.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This table is used to organize and keep track of flights of occurrences, which are
 * organized by occurrence number.  Adding new entries to this table should be the
 * responsibility of the Daily Job when it creates a new flight of occurrences for
 * a schedule.
 *
 * Updating this table should be the responsibility of the cron job that calculates
 * the answer threshold of a flight of occurrences.  When all occurrences of a flight
 * are marked complete, that job should update the isClosed value for that row to true
 * and that flight will not be checked for completion again.
 *
 * @author hmccardell
 */

@Entity
@Audited
public class Flight implements Serializable {

  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "schedule_id")
  private long scheduleId;

  @Column(name = "schedule_is_active")
  private boolean scheduleIsActive;

  @Column(name = "flight_number")
  private long flightNumber;

  @Column(name = "is_closed")
  private boolean isClosed;

  @Column(name = "number_of_occurrences")
  private long numberOfOccurrences;

  public Flight(){}

  public Flight(long scheduleId, boolean scheduleIsActive, long flightNumber, boolean isClosed, long numberOfOccurrences) {
    this.scheduleId = scheduleId;
    this.scheduleIsActive = scheduleIsActive;
    this.flightNumber = flightNumber;
    this.isClosed = isClosed;
    this.numberOfOccurrences = numberOfOccurrences;
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
   * Sets new isClosed.
   *
   * @param isClosed New value of isClosed.
   */
  public void setIsClosed(boolean isClosed) {
    this.isClosed = isClosed;
  }

  /**
   * Gets numberOfOccurrences.
   *
   * @return Value of numberOfOccurrences.
   */
  public long getNumberOfOccurrences() {
    return numberOfOccurrences;
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
   * Gets isClosed.
   *
   * @return Value of isClosed.
   */
  public boolean getIsClosed() {
    return isClosed;
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
   * Sets new flightNumber.
   *
   * @param flightNumber New value of flightNumber.
   */
  public void setFlightNumber(long flightNumber) {
    this.flightNumber = flightNumber;
  }

  /**
   * Gets scheduleIsActive.
   *
   * @return Value of scheduleIsActive.
   */
  public boolean getScheduleIsActive() {
    return scheduleIsActive;
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
   * Gets scheduleId.
   *
   * @return Value of scheduleId.
   */
  public long getScheduleId() {
    return scheduleId;
  }

  /**
   * Sets new numberOfOccurrences.
   *
   * @param numberOfOccurrences New value of numberOfOccurrences.
   */
  public void setNumberOfOccurrences(long numberOfOccurrences) {
    this.numberOfOccurrences = numberOfOccurrences;
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
   * Sets new scheduleIsActive.
   *
   * @param scheduleIsActive New value of scheduleIsActive.
   */
  public void setScheduleIsActive(boolean scheduleIsActive) {
    this.scheduleIsActive = scheduleIsActive;
  }
}

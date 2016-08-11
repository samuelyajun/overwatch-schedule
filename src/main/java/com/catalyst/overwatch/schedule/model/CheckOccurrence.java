package com.catalyst.overwatch.schedule.model;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Audited
@Entity
public class CheckOccurrence implements Serializable {

  private static final long serialVersionUID = -4951321295232200246L;

  @Column(name = "schedule_id")
  private long scheduleId;

  @Column(name = "occurrence_number")
  private long occurrenceNumber;

  @Column(name = "is_closed")
  private boolean isClosed;

  public CheckOccurrence(){}

  public CheckOccurrence(boolean isClosed, long occurrenceNumber) {
    this.isClosed = isClosed;
    this.occurrenceNumber = occurrenceNumber;
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
   * Sets new occurrenceNumber.
   *
   * @param occurrenceNumber New value of occurrenceNumber.
   */
  public void setOccurrenceNumber(long occurrenceNumber) {
    this.occurrenceNumber = occurrenceNumber;
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
   * Gets scheduleId.
   *
   * @return Value of scheduleId.
   */
  public long getScheduleId() {
    return scheduleId;
  }

  /**
   * Gets occurrenceNumber.
   *
   * @return Value of occurrenceNumber.
   */
  public long getOccurrenceNumber() {
    return occurrenceNumber;
  }

  /**
   * Gets isClosed.
   *
   * @return Value of isClosed.
   */
  public boolean isIsClosed() {
    return isClosed;
  }

  /**
   * Sets new serialVersionUID.
   *
   * @param serialVersionUID New value of serialVersionUID.
   */
  public static void setSerialVersionUID(long serialVersionUID) {
    serialVersionUID = serialVersionUID;
  }

  /**
   * Sets new scheduleId.
   *
   * @param scheduleId New value of scheduleId.
   */
  public void setScheduleId(long scheduleId) {
    this.scheduleId = scheduleId;
  }

  @Override
  public String toString() {
    return "CheckOccurrence{" +
            "scheduleId=" + scheduleId +
            ", occurrenceNumber=" + occurrenceNumber +
            ", isClosed=" + isClosed +
            '}';
  }
}

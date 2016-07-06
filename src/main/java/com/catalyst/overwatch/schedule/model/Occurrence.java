package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "occurrence")
public class Occurrence implements Serializable {
  
  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToMany
  @JoinColumn(name = "id", nullable = false)
  @Column(name = "schedule_id")
  private Collection<Schedule> schedules;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "time_stamp")
  private LocalDateTime timeStamp;
  
  @Column(name = "occurrence_number")
  private long occurrenceNumber;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Collection<Schedule> getSchedules() {
    return schedules;
  }

  public void setSchedules(Collection<Schedule> schedules) {
    this.schedules = schedules;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDateTime getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(LocalDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  public long getOccurrenceNumber() {
    return occurrenceNumber;
  }

  public void setOccurrenceNumber(long occurrenceNumber) {
    this.occurrenceNumber = occurrenceNumber;
  }
 
}

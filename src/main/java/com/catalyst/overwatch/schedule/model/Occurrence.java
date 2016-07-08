package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Occurrence implements Serializable {
  
  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne
  @JoinColumn(name = "schedule_id")
  private Schedule schedule;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "create_date")
  private LocalDate createDate;
  
  @Column(name = "time_to_live")
  private LocalDateTime timeToLive;
  
  // may not need occurrence number
  //@Column(name = "occurrence_number")
  //private long occurrenceNumber;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setSchedules(Schedule schedule) {
    this.schedule = schedule;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDateTime getTimeToLive() {
    return timeToLive;
  }

  public void setTimeToLive(LocalDateTime timeToLive) {
    this.timeToLive = timeToLive;
  }

  /*public long getOccurrenceNumber() {
    return occurrenceNumber;
  }

  public void setOccurrenceNumber(long occurrenceNumber) {
    this.occurrenceNumber = occurrenceNumber;
  }*/
 
}

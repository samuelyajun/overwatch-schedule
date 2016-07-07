package com.catalyst.overwatch.schedule.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bpyl on 6/14/2016.
 */
@Entity
@Audited
public class Schedule implements Serializable {
    private static final long serialVersionUID = -4951321295232200246L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Start date cannot be null")
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ElementCollection(targetClass = Days.class)
    @CollectionTable(name = "days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "days_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Days> daysOfWeek = new HashSet<Days>();    
     
    @Column(name = "frequency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    
    @NotNull(message = "Username cannot be null")
    @Column(name = "username")
    private String username;
    
    @NotNull(message = "Survey cannot be null")
    @Column(name = "survey")
    private String survey;    
    
    @NotNull(message = "Attributes cannot be null")   
    @Column(name = "attributes")
    private ScheduleAttributes attributes;    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Days> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Set<Days> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
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

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getSurvey() {
      return survey;
    }

    public void setSurvey(String survey) {
      this.survey = survey;
    }

    public ScheduleAttributes getAttributes() {
      return attributes;
    }

    public void setAttributes(ScheduleAttributes attributes) {
      this.attributes = attributes;
    }

}

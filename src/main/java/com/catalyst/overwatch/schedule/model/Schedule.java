package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

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
    
    @Column(name = "frequency")
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    
    @Column(name = "interval")
    private String interval;
   
    @Column(name = "survey")
    private String survey;
    
    @OneToMany
    @JoinColumn(name="schedule_id")
    private Set<Respondent> respondents;

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

    public String getSurvey() {
      return survey;
    }

    public void setSurvey(String survey) {
      this.survey = survey;
    }

    public String getInterval() {
      return interval;
    }

    public void setInterval(String interval) {
      this.interval = interval;
    }

    public Set<Respondent> getRespondents() {
      return respondents;
    }

    public void setRespondents(Set<Respondent> respondents) {
      this.respondents = respondents;
    }
}

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
}

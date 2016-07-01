package com.catalyst.overwatch.schedule.model;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by bpyl on 6/14/2016.
 */
@Entity
@Audited
public class Schedule implements Serializable {
    private static final long serialVersionUID = -4951321295232200246L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    private Date endDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

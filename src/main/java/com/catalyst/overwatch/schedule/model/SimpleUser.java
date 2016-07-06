package com.catalyst.overwatch.schedule.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class SimpleUser extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

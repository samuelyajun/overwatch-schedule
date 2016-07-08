package com.catalyst.overwatch.schedule.model;

import javax.persistence.*;

@Entity
@Table(name = "attribute_types")
public class AttributeType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

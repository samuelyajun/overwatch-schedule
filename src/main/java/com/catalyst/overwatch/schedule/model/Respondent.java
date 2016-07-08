package com.catalyst.overwatch.schedule.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "respondents")
public class Respondent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private User user;
    @ManyToMany
    private Set<AllowedAttribute> attributes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<AllowedAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<AllowedAttribute> attributes) {
        this.attributes = attributes;
    }
}

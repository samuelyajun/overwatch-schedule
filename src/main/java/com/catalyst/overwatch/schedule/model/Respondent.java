package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Respondent implements Serializable {
  
  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;  
  
  @OneToMany
  private Collection<AllowedAttributes> allowedAttributes;
  
  @ManyToOne
  private Schedule schedule;
  
  @OneToMany
  private Collection<Users> users;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
 
  public Collection<AllowedAttributes> getAllowedAttributes() {
    return allowedAttributes;
  }

  public void setAllowedAttributes(Collection<AllowedAttributes> allowedAttributes) {
    this.allowedAttributes = allowedAttributes;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  public Collection<Users> getUsers() {
    return users;
  }

  public void setUsers(Collection<Users> users) {
    this.users = users;
  }

}

package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class AttributeType implements Serializable  {
  
  private static final long serialVersionUID = -4951321295232200246L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  private long id;  
  
  @Column
  private String name;

  public long getid() {
    return id;
  }

  public void setAttributeTypeId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

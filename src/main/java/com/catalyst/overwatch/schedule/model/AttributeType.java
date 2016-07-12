package com.catalyst.overwatch.schedule.model;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

@Entity
@Audited(targetAuditMode = NOT_AUDITED)
public class AttributeType implements Serializable  {
  
  private static final long serialVersionUID = -4951321295232200246L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)  
  private long id;  
  
  @Column(name = "name")
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

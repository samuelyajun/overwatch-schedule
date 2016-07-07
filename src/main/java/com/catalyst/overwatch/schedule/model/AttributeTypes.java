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
public class AttributeTypes implements Serializable  {
  
  private static final long serialVersionUID = -4951321295232200246L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "attribute_type_id")
  private long attributeTypeId;  
  
  @Column(name = "name")
  private String name;

  public long getAttributeTypeId() {
    return attributeTypeId;
  }

  public void setAttributeTypeId(long attributeTypeId) {
    this.attributeTypeId = attributeTypeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

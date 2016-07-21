package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class AllowedAttribute implements Serializable {

  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(cascade = {CascadeType.ALL})
  @OrderColumn  
  private AttributeType attributeType;

  @Column(name = "attribute_value")
  @OrderColumn  
  private String attributeValue;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public AttributeType getAttributeTypes() {
    return attributeType;
  }

  public void setAttributeTypes(AttributeType attributeType) {
    this.attributeType = attributeType;
  }

  public String getAttributeValue() {
    return attributeValue;
  }

  public void setAttributeValue(String attributeValue) {
    this.attributeValue = attributeValue;
  }

}

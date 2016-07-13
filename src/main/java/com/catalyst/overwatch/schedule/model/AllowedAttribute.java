package com.catalyst.overwatch.schedule.model;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

@Entity
@Audited(targetAuditMode = NOT_AUDITED)
public class AllowedAttribute implements Serializable {

  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne(cascade = {CascadeType.ALL})
  private AttributeType attributeType;

  @Column(name = "attribute_value")
  private String value;

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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}

package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class AllowedAttributes implements Serializable {
  
  private static final long serialVersionUID = -4951321295232200246L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToMany
  @JoinColumn(name = "type_id")  
  private Collection<AttributeTypes> attributeTypes;
  
  @Column(name = "attribute_value")
  private String value;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Collection<AttributeTypes> getAttributeTypes() {
    return attributeTypes;
  }

  public void setAttributeTypes(Collection<AttributeTypes> attributeTypes) {
    this.attributeTypes = attributeTypes;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}

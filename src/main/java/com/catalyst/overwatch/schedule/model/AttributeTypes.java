package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "attribute_types")
public class AttributeTypes implements Serializable  {
  
  private static final long serialVersionUID = -4951321295232200246L;  

  @Column(name = "id")
  private long id;
  
  @Column(name = "name")
  private String name;
}

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
  private long id;

  //@Column(name = "id")
  //private long id;
  
  @Column(name = "name")
  private String name;
}

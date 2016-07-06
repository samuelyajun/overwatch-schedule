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
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "allowed_attributes")
public class AllowedAttributes implements Serializable {
  
  private static final long serialVersionUID = -4951321295232200246L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToMany
  @JoinColumn(name = "id")  
  private Collection<AttributeTypes> attributeTypes;
  
  @Column(name = "value")
  private String value;

}

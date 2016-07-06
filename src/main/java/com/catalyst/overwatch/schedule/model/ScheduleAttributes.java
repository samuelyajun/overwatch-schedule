package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "schedule_attributes")
public class ScheduleAttributes implements Serializable {
  
  private static final long serialVersionUID = -4951321295232200246L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @OneToMany  
  @JoinColumn(name = "id", nullable = false)
  private Collection<Schedule> schedules;  
  
  @ManyToOne 
  @JoinColumn(name = "type_id", nullable = false)
  private AllowedAttributes allowedAttributes;
  
  @ManyToMany  
  @JoinColumn(name = "id", nullable = false)
  private AttributeTypes attributeTypes;

}

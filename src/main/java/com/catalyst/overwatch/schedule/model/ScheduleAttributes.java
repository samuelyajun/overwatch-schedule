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
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class ScheduleAttributes implements Serializable {
  
  private static final long serialVersionUID = -4951321295232200246L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  //@OneToMany
  //@JoinColumn(name = "id", nullable = false)
  //private Collection<Schedule> schedules;
  
  @Column(name = "schedule_id")
  private Schedule schedule;
  
  @ManyToOne  
  @JoinColumn(name = "type_id")  
  private AllowedAttributes allowedAttributes;
  
  @ManyToMany  
  @JoinColumn(name = "attribute_type_id")
  private Collection<AttributeTypes> attributeTypes;

}

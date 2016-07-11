package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Audited(targetAuditMode = NOT_AUDITED)
public class Respondent implements Serializable {
  
  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;  
  
  @ManyToMany (cascade = {CascadeType.ALL})
  private Set<AllowedAttribute> allowedAttributes; 
  
  @ManyToOne (cascade = {CascadeType.ALL})
  private User user;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
 
  public Set<AllowedAttribute> getAllowedAttributes() {
    return allowedAttributes;
  }

  public void setAllowedAttributes(Set<AllowedAttribute> allowedAttributes) {
    this.allowedAttributes = allowedAttributes;
  }  

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}

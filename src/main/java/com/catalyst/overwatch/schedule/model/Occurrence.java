package com.catalyst.overwatch.schedule.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Audited(targetAuditMode = NOT_AUDITED)
public class Occurrence implements Serializable {
  
  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id; 
  
  @ManyToOne (cascade = {CascadeType.ALL})
  private Respondent respondent;
  
  @Column(name = "generation_date")
  private LocalDateTime generationDate;
  
  @Column(name = "is_complete")
  private boolean isComplete;
  
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Respondent getRespondent() {
    return respondent;
  }

  public void setRespondent(Respondent respondent) {
    this.respondent = respondent;
  }

  public LocalDateTime getGenerationDate() {
    return generationDate;
  }

  public void setGenerationDate(LocalDateTime generationDate) {
    this.generationDate = generationDate;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public void setComplete(boolean isComplete) {
    this.isComplete = isComplete;
  }
}

package com.catalyst.overwatch.schedule.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Audited
public class Occurrence implements Serializable {

  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(cascade = {CascadeType.MERGE})
  private Respondent respondent;

  @Column
  private LocalDate generationDate;

  @Column(name = "is_complete")
  private boolean isComplete;

  public Occurrence() {
  }

  public Occurrence(Respondent respondent) {
    isComplete = false;
    this.respondent = respondent;
    this.generationDate = LocalDate.now();
  }

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

  public LocalDate getGenerationDate() {
    return generationDate;
  }

  public void setGenerationDate(LocalDate generationDate) {
    this.generationDate = generationDate;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public void setComplete(boolean isComplete) {
    this.isComplete = isComplete;
  }

  @Override
  public String toString() {
    return "Occurrence{" +
            "id=" + id +
            ", respondent=" + respondent +
            ", generationDate=" + generationDate +
            ", isComplete=" + isComplete +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Occurrence that = (Occurrence) o;

    if (isComplete != that.isComplete) return false;
    if (!respondent.equals(that.respondent)) return false;
    return generationDate.equals(that.generationDate);

  }

}

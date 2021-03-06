package com.catalyst.overwatch.schedule.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Audited
public class Respondent implements Serializable {

  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private Set<AllowedAttribute> allowedAttributes;

  @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
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

  @Override
  public String toString() {
    return "Respondent{" +
            "id=" + id +
            ", allowedAttributes=" + allowedAttributes +
            ", user=" + user +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Respondent that = (Respondent) o;

    if (!allowedAttributes.equals(that.allowedAttributes)) return false;
    return user.equals(that.user);

  }
}

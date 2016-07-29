package com.catalyst.overwatch.schedule.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Audited
public class AllowedAttribute implements Serializable {

  private static final long serialVersionUID = -4951321295232200246L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
  @OrderColumn
  private AttributeType attributeType;

  @Column(name = "attribute_value")
  @OrderColumn
  private String attributeValue;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public AttributeType getAttributeTypes() {
    return attributeType;
  }

  public void setAttributeTypes(AttributeType attributeType) {
    this.attributeType = attributeType;
  }

  public String getAttributeValue() {
    return attributeValue;
  }

  public void setAttributeValue(String attributeValue) {
    this.attributeValue = attributeValue;
  }

  @Override
  public String toString() {
    return "AllowedAttribute{" +
            "id=" + id +
            ", attributeType=" + attributeType +
            ", attributeValue='" + attributeValue + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AllowedAttribute that = (AllowedAttribute) o;

    if (!attributeType.equals(that.attributeType)) return false;
    return attributeValue.equals(that.attributeValue);

  }

  @Override
  public int hashCode() {
    int result = attributeType.hashCode();
    result = 31 * result + attributeValue.hashCode();
    return result;
  }
}

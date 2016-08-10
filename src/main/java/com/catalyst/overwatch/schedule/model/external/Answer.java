package com.catalyst.overwatch.schedule.model.external;

/**
 * An external class that is implemented in the SurveyResponse service.  Holds the
 * answer data from a SurveyResponse. *
 *
 * @author hmccardell
 */
public class Answer {

  private long id;

  private String questionLink;

  private String value;

  /**
   * Gets id.
   *
   * @return Value of id.
   */
  public long getId() {
    return id;
  }

  /**
   * Gets value.
   *
   * @return Value of value.
   */
  public String getValue() {
    return value;
  }

  /**
   * Gets questionLink.
   *
   * @return Value of questionLink.
   */
  public String getQuestionLink() {
    return questionLink;
  }

  /**
   * Sets new questionLink.
   *
   * @param questionLink New value of questionLink.
   */
  public void setQuestionLink(String questionLink) {
    this.questionLink = questionLink;
  }

  /**
   * Sets new id.
   *
   * @param id New value of id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Sets new value.
   *
   * @param value New value of value.
   */
  public void setValue(String value) {
    this.value = value;
  }

  public Answer(){}

  public Answer(String questionLink, String value) {
    this.questionLink = questionLink;
    this.value = value;
  }

  @Override
  public String toString() {
    return "Answer{" +
            "id=" + id +
            ", questionLink='" + questionLink + '\'' +
            ", value='" + value + '\'' +
            '}';
  }
}

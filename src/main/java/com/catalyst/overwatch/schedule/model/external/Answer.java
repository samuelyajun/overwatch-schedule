package com.catalyst.overwatch.schedule.model.external;

/**
 * Created by hmccardell on 8/4/2016.
 */
public class Answer {

  private long id;

  private String questionLink;

  private String value;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getQuestionLink() {
    return questionLink;
  }

  public void setQuestionLink(String questionLink) {
    this.questionLink = questionLink;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}

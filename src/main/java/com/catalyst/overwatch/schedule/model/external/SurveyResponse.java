package com.catalyst.overwatch.schedule.model.external;

import java.util.List;

/**
 * An Object to hold SurveyResponse data. This is how the Schedule service determines if an
 * occurrence has been 'completed'.  These objects only exist on the SurveyResponse service
 * if an answer has been submitted for that occurrenceId.
 *
 * @author hmccardell
 */
public class SurveyResponse {

  private long id;

  private String uniqueSurveyId;

  private String originatorId;

  private String dateAnswered;

  private List<Answer> answers;

  /**
   * Sets new originatorId.
   *
   * @param originatorId New value of originatorId.
   */
  public void setOriginatorId(String originatorId) {
    this.originatorId = originatorId;
  }

  /**
   * Gets uniqueSurveyId.
   *
   * @return Value of uniqueSurveyId.
   */
  public String getUniqueSurveyId() {
    return uniqueSurveyId;
  }

  /**
   * Sets new dateAnswered.
   *
   * @param dateAnswered New value of dateAnswered.
   */
  public void setDateAnswered(String dateAnswered) {
    this.dateAnswered = dateAnswered;
  }

  /**
   * Gets id.
   *
   * @return Value of id.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets new uniqueSurveyId.
   *
   * @param uniqueSurveyId New value of uniqueSurveyId.
   */
  public void setUniqueSurveyId(String uniqueSurveyId) {
    this.uniqueSurveyId = uniqueSurveyId;
  }

  /**
   * Sets new answers.
   *
   * @param answers New value of answers.
   */
  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  /**
   * Gets dateAnswered.
   *
   * @return Value of dateAnswered.
   */
  public String getDateAnswered() {
    return dateAnswered;
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
   * Gets answers.
   *
   * @return Value of answers.
   */
  public List<Answer> getAnswers() {
    return answers;
  }

  /**
   * Gets originatorId.
   *
   * @return Value of originatorId.
   */
  public String getOriginatorId() {
    return originatorId;
  }

  @Override
  public String toString() {
    return "SurveyResponse{" +
            "id=" + id +
            ", uniqueSurveyId='" + uniqueSurveyId + '\'' +
            ", originatorId='" + originatorId + '\'' +
            ", dateAnswered='" + dateAnswered + '\'' +
            ", answers=" + answers +
            '}';
  }
}

package com.catalyst.overwatch.schedule.model.external;

import java.util.List;

/**
 * Created by hmccardell on 8/3/2016.
 */
public class SurveyResponse {

  private long id;

  private String uniqueSurveyId;

  private String originatorId;

  private List<Answer> answers;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUniqueSurveyId() {
    return uniqueSurveyId;
  }

  public void setUniqueSurveyId(String uniqueSurveyId) {
    this.uniqueSurveyId = uniqueSurveyId;
  }

  public String getOriginatorId() {
    return originatorId;
  }

  public void setOriginatorId(String originatorId) {
    this.originatorId = originatorId;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  @Override
  public String toString() {
    return "SurveyResponse{" +
            "id=" + id +
            ", uniqueSurveyId='" + uniqueSurveyId + '\'' +
            ", originatorId='" + originatorId + '\'' +
            ", answers=" + answers +
            '}';
  }
}

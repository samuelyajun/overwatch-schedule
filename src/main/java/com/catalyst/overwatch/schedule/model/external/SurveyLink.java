package com.catalyst.overwatch.schedule.model.external;

import com.google.common.base.Objects;

/**
 * This class is used to encapsulate data from the survey service.
 *
 * @author hmccardell
 */
public class SurveyLink {

  String surveyDisplayLink;

  String surveyName;

  String templateLink;

  public String getSurveyName() {
    return surveyName;
  }

  public void setSurveyName(String surveyName) {
    this.surveyName = surveyName;
  }

  public String getTemplateLink() {
    return templateLink;
  }

  public void setTemplateLink(String templateLink) {
    this.templateLink = templateLink;
  }

  public String getSurveyDisplayLink() {
    return surveyDisplayLink;
  }

  public void setSurveyDisplayLink(String surveyDisplayLink) {
    this.surveyDisplayLink = surveyDisplayLink;
  }

  public SurveyLink() {
  }

  public SurveyLink(String surveyName, String templateLink) {
    this.surveyName = surveyName;
    this.templateLink = templateLink;
  }

  @Override
  public String toString() {
    return "SurveyLink{" +
            "surveyDisplayLink='" + surveyDisplayLink + '\'' +
            ", surveyName='" + surveyName + '\'' +
            ", templateLink='" + templateLink + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SurveyLink that = (SurveyLink) o;
    return Objects.equal(surveyDisplayLink, that.surveyDisplayLink) &&
            Objects.equal(surveyName, that.surveyName) &&
            Objects.equal(templateLink, that.templateLink);
  }

}

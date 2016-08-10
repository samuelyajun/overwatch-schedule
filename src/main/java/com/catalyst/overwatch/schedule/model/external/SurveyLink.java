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

  /**
   * Gets surveyName.
   *
   * @return Value of surveyName.
   */
  public String getSurveyName() {
    return surveyName;
  }

  /**
   * Gets surveyDisplayLink.
   *
   * @return Value of surveyDisplayLink.
   */
  public String getSurveyDisplayLink() {
    return surveyDisplayLink;
  }

  /**
   * Sets new surveyName.
   *
   * @param surveyName New value of surveyName.
   */
  public void setSurveyName(String surveyName) {
    this.surveyName = surveyName;
  }

  /**
   * Sets new templateLink.
   *
   * @param templateLink New value of templateLink.
   */
  public void setTemplateLink(String templateLink) {
    this.templateLink = templateLink;
  }

  /**
   * Sets new surveyDisplayLink.
   *
   * @param surveyDisplayLink New value of surveyDisplayLink.
   */
  public void setSurveyDisplayLink(String surveyDisplayLink) {
    this.surveyDisplayLink = surveyDisplayLink;
  }

  /**
   * Gets templateLink.
   *
   * @return Value of templateLink.
   */
  public String getTemplateLink() {
    return templateLink;
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

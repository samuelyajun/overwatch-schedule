package com.catalyst.overwatch.schedule.model.external;

/**
 * Created by hmccardell on 8/4/2016.
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

  public SurveyLink(){}

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
}

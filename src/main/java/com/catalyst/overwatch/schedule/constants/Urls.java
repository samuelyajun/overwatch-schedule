package com.catalyst.overwatch.schedule.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class that contains link references to other services that the Overwatch
 * schedule service is communicating with.
 *
 * @author Gavin Stringfellow <gstringfellow@catalystdevworks.com>
 * @author Jordan Barroga <jbarroga@catalystdevworks.com>
 */
@Component
public class Urls {

    private static final String NOTIFICATION_ENDPOINT = "http://%s:%s/notify";

    private static final String FRONT_END_BASE_URL = "http://%s:%s";

    private static final String SURVEY_ENDPOINT = "http://%s:%s/surveys";

    public static final String RESPONSE_BASE_URL = "http://%s:%s";

    public static final String SURVEY_RESPONSE = "/surveyResponses";

    public static final String QUERY_BY_DATE = "/search/findSurveyResponseByDateAnswered?dateAnswered=";

    @Value("${frontend.domain}")
    private String frontendDomain;

    @Value("${frontend.port}")
    private String frontendPort;

    @Value("${notification.domain}")
    private String notificationDomain;

    @Value("${notification.port}")
    private String notificationPort;

    @Value("${survey.domain}")
    private String surveyDomain;

    @Value("${survey.port}")
    private String surveyPort;

    @Value("${response.domain}")
    private String responseDomain;

    @Value("${response.port}")
    private String responsePort;

    public String getNotificationEndpoint() {
        return String.format(NOTIFICATION_ENDPOINT, notificationDomain, notificationPort);
    }

    public String getFrontEndBaseUrl() {
        return String.format(FRONT_END_BASE_URL, frontendDomain, frontendPort);
    }

    public String getSurveyEndpoint() {
        return String.format(SURVEY_ENDPOINT, surveyDomain, surveyPort);
    }

    public String getResponseBaseUrl() {
        return String.format(RESPONSE_BASE_URL, responseDomain, responsePort);
    }

    public String getSearchSurveyResponseByDate() {
        return getResponseBaseUrl() + SURVEY_RESPONSE + QUERY_BY_DATE;
    }

}

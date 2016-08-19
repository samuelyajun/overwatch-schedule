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

    /**
     * Builds the notification endpoint 
     * Generated from the values in the respective local or docker properties file
     *
     * @return String - fully formed notification endpoint string
     * 	Example: "http://someDomain:somePort/notify"
     */
    public String getNotificationEndpoint() {
        return String.format(NOTIFICATION_ENDPOINT, notificationDomain, notificationPort);
    }
    
    /**
     * Builds the front end base url 
     * Generated from the values in the respective local or docker properties file
     *
     * @return String - front end base url 
     */
    public String getFrontEndBaseUrl() {
        return String.format(FRONT_END_BASE_URL, frontendDomain, frontendPort);
    }

    /**
     * Builds the survey service endpoint 
     * Generated from the values in the respective local or docker properties file
     *
     * @return String - fully formed survey service endpoint 
     * 	Example: "http://someDomain:somePort/surveys"
     */
    public String getSurveyEndpoint() {
        return String.format(SURVEY_ENDPOINT, surveyDomain, surveyPort);
    }
    
    /**
     * Builds the survey frontend endpoint for survey email links 
     * Generated from the values in the respective local or docker properties file
     * 
     *
     * @return String - fully formed survey service endpoint 
     */
    public String getSurveyURLEndpoint() {
        return String.format(SURVEY_ENDPOINT, frontendDomain, frontendPort);
    }

    /**
     * Builds the response service base url
     * Generated from the values in the respective local or docker properties file
     *
     * @return String -	response service base url 
     */
    public String getResponseBaseUrl() {
        return String.format(RESPONSE_BASE_URL, responseDomain, responsePort);
    }

    /**
     * Builds the response endpoint for searching survey responses by date 
     * Generated from the values in the respective local or docker properties file
     *
     * @return String - response endpoint for searching by date
     * 	Example: "http://someDomain: somePort/surveyResponses/search/
     * 	findSurveyResponseByDateAnswered?dateAnswered="
     */
    public String getSearchSurveyResponseByDate() {
        return getResponseBaseUrl() + SURVEY_RESPONSE + QUERY_BY_DATE;
    }

}

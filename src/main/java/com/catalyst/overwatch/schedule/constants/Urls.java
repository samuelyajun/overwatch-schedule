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

    @Value("${notification.domain}")
    private String notificationDomain;

    @Value("${notification.port}")
    private String notificationPort;

    public String getNotificationEndpoint() {
        return String.format(NOTIFICATION_ENDPOINT,notificationDomain,notificationPort);
    }

    public String getFrontEndBaseUrl() {
        return String.format(FRONT_END_BASE_URL,notificationDomain,notificationPort);
    }

}

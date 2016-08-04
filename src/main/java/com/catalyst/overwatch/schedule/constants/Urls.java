package com.catalyst.overwatch.schedule.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by gstringfellow on 8/3/16.
 */
@Configurable
public class Urls {

    private static final String NOTIFICATION_ENDPOINT = "http://%s:%s/notify";
    private static final String FRONT_END_BASE_URL = "http://%s:%s";
    @Autowired
    private static Urls urls;

    @Value("${notification.domain}")
    private String notificationDomain;

    public String getNotificationEndpoint() {
        return String.format(NOTIFICATION_ENDPOINT,notificationDomain,notificationPort);
    }

    public String getFrontEndBaseUrl() {
        return String.format(FRONT_END_BASE_URL,notificationDomain,notificationPort);
    }

    @Value("${notification.port}")
    private String notificationPort;


    public static Urls getInstance(){
        return urls;
    }


}

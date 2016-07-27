package com.catalyst.overwatch.schedule.model;

import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by hmccardell on 7/18/2016.
 */
@Component
public class Notification {

    private String[] recipientAddresses;
    private String subject, body;

    public Notification(){};

    public Notification(String[] recipientAddresses, String subject, String body) {
        this.recipientAddresses = recipientAddresses;
        this.subject = subject;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getRecipientAddresses() {
        return recipientAddresses;
    }

    public void setRecipientAddresses(String[] recipientAddresses) {
        this.recipientAddresses = recipientAddresses;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "recipientAddresses=" + Arrays.toString(recipientAddresses) +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

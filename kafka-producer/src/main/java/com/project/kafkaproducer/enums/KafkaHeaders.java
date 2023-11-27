package com.project.kafkaproducer.enums;

public enum KafkaHeaders {
    CONTENT_TYPE("contentType"),
    APPLICATION_NAME("applicationName"),
    EVENT_ID("eventId"),
    EVENT_NAME("eventName");

    private String value;

    KafkaHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}

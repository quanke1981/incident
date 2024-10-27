package com.example.incident.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum IncidentStatus {
    OPEN,
    IN_PROGRESS,
    CLOSED;


    @JsonCreator
    public static IncidentStatus fromString(String value) {
        return IncidentStatus.valueOf(value.toUpperCase());
    }

}

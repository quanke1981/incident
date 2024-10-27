package com.example.incident.model;

import com.example.incident.exception.InvalidIncidentStatusException;
import com.fasterxml.jackson.annotation.JsonCreator;
import jdk.jshell.Snippet;

public enum IncidentStatus {
    OPEN,
    IN_PROGRESS,
    CLOSED;


    @JsonCreator
    public static IncidentStatus fromString(String value) {
        try {
            return IncidentStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidIncidentStatusException("Invalid Incident Status: " + value);
        }
    }

}

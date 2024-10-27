package com.example.incident.exception;

public class IncidentNotFoundException extends RuntimeException {
    public IncidentNotFoundException() {
    }

    public IncidentNotFoundException(String message) {
        super(message);
    }

    public IncidentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncidentNotFoundException(Throwable cause) {
        super(cause);
    }
}
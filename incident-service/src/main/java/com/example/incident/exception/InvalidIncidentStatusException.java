package com.example.incident.exception;

public class InvalidIncidentStatusException extends RuntimeException {
    public InvalidIncidentStatusException() {
    }

    public InvalidIncidentStatusException(String message) {
        super(message);
    }

    public InvalidIncidentStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIncidentStatusException(Throwable cause) {
        super(cause);
    }
}
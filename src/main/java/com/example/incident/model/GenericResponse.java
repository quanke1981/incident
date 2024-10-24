package com.example.incident.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResponse<R> {
    private boolean success;
    private R responseData;
    private String message;
    private Integer errorCode;

    public GenericResponse(boolean success, R responseData, Integer errorCode, String message) {
        this.success = success;
        this.responseData = responseData;
        this.errorCode = errorCode;
        this.message = message;
    }

    public GenericResponse(boolean success, R responseData) {
        this(success, responseData, 0, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public R getResponseData() {
        return responseData;
    }

    public void setResponseData(R responseData) {
        this.responseData = responseData;
    }
}

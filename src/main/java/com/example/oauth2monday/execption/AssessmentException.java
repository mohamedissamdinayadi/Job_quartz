package com.example.oauth2monday.execption;

import org.springframework.http.HttpStatus;

public class AssessmentException extends RuntimeException {

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    public AssessmentException(int code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

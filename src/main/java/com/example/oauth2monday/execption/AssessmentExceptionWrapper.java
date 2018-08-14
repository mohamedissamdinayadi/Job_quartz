package com.example.oauth2monday.execption;

public class AssessmentExceptionWrapper {

    private final int code;
    private final String description;

    public AssessmentExceptionWrapper(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

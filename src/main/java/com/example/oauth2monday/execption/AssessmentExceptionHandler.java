package com.example.oauth2monday.execption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AssessmentExceptionHandler {

    @ExceptionHandler(AssessmentException.class)
    public ResponseEntity<AssessmentExceptionWrapper> handleException(AssessmentException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(new AssessmentExceptionWrapper(e.getCode(), e.getDescription()));
    }


}

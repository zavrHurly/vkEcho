package com.example.vkecho.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CallBackResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String DEFAULT_RESPONSE = "ok";

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<Object> handleOk(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, DEFAULT_RESPONSE, null, HttpStatus.OK, request);
    }
}

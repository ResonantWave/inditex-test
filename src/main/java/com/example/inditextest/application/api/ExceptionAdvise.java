package com.example.inditextest.application.api;

import com.example.inditextest.domain.exception.PriceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvise extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { PriceNotFoundException.class })
    protected ResponseEntity<Object> handlePriceNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleGenericException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

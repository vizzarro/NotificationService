package com.notificationrequest.services;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RequestNotificationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoElement(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Element not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleEmpty(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "The element is empty";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleCostraint(
            ConstraintViolationException ex, WebRequest request) {
        ConstraintViolationException exception;
        List<String> errorMessages = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return handleExceptionInternal(ex, errorMessages.toString(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntime(
            RuntimeException ex, WebRequest request) {
        ConstraintViolationException exception;
        String errorMessages = ex.getMessage();
        return handleExceptionInternal(ex, errorMessages,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(ResourceAccessException.class)
    protected ResponseEntity<Object> handleResource(
            ResourceAccessException ex, WebRequest request) {
        ConstraintViolationException exception;
        String errorMessages = ex.getMessage();
        return handleExceptionInternal(ex, errorMessages,
                new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }
}

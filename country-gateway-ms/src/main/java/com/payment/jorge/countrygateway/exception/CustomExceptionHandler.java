package com.payment.jorge.countrygateway.exception;

import com.payment.jorge.countrygateway.ErrorResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    protected ErrorResponse getErrorResponse(RuntimeException ex, HttpStatus status) {
        return new ErrorResponse.Builder()
                .withStatus(status)
                .withMessage(ex.getMessage())
                .withCode(status.value())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors()
                .forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));
        ErrorResponse errorResponse = createErrorResponse(status, errors);
        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final List<String> errors = new ArrayList<String>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors()
                .forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));
        ErrorResponse errorResponse = createErrorResponse(status, errors);
        return handleExceptionInternal(ex, errorResponse, headers, errorResponse.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getValue() + " value should be of type " + ex.getRequiredType();
        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .withStatus(status)
                .withMessage("Invalid type")
                .withErrors(Collections.singletonList(error))
                .withCode(status.value())
                .build();
        return handleExceptionInternal(ex, errorResponse, headers, errorResponse.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .withStatus(status)
                .withMessage("Invalid fields")
                .withCode(status.value())
                .withErrors(Stream.of(ex.getCause())
                        .map(Throwable::getMessage)
                        .collect(toList()))
                .build();
        return handleExceptionInternal(ex, errorResponse, headers, errorResponse.getStatus(), request);
    }

    private ErrorResponse createErrorResponse(HttpStatus status, List<String> errors) {
        return new ErrorResponse.Builder()
                .withStatus(status)
                .withMessage("Invalid fields")
                .withErrors(errors)
                .withCode(status.value()).build();
    }

}

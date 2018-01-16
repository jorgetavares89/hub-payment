package com.payment.jorge.paymentmx.exception;

import com.payment.jorge.paymentmx.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    protected ErrorResponse getErrorResponse(RuntimeException ex, HttpStatus status) {
        return new ErrorResponse.Builder()
                .withStatus(status)
                .withMessage(ex.getMessage())
                .withError(ex.getClass()
                        .getSimpleName())
                .withCode(status.value())
                .build();
    }
}

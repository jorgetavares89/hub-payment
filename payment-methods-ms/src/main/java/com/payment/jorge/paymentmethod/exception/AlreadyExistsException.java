package com.payment.jorge.paymentmethod.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }

    private static final long serialVersionUID = -4233753300667670068L;
}

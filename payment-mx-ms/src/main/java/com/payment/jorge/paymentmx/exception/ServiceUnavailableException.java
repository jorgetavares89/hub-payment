package com.payment.jorge.paymentmx.exception;

public class ServiceUnavailableException extends RuntimeException{
	private static final long serialVersionUID = -4233753300667670069L;
	public ServiceUnavailableException(String message) {
        super(message);
    }
}

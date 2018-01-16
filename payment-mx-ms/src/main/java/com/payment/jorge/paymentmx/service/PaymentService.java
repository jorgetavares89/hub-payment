package com.payment.jorge.paymentmx.service;

import com.payment.jorge.paymentmx.exception.BadRequestException;
import com.payment.jorge.paymentmx.model.enums.CountryCode;
import com.payment.jorge.paymentmx.model.factory.PaymentFactory;
import com.payment.jorge.paymentmx.model.request.PaymentRequest;
import com.payment.jorge.paymentmx.model.result.PaymentResult;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentFactory factory;

    public PaymentService(PaymentFactory factory) {
        this.factory = factory;
    }

    public PaymentResult process(PaymentRequest paymentRequest) {
        if (paymentRequest.getCountryCode().equals(CountryCode.MX)) {
            return factory.createResult(paymentRequest.getRestaurantId(),
                                        paymentRequest.getUserId(),
                                        "Successfully MX payment");
        } else {
            throw new BadRequestException("Invalid Country, try use MX");
        }

    }
}

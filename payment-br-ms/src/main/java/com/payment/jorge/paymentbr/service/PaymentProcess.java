package com.payment.jorge.paymentbr.service;

import com.payment.jorge.paymentbr.model.request.PaymentRequest;
import com.payment.jorge.paymentbr.model.result.PaymentResult;

public interface PaymentProcess {
    PaymentResult process(PaymentRequest request);
}

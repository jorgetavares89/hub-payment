package com.payment.jorge.paymentbr.service.digitalwallet;

import com.payment.jorge.paymentbr.model.factory.PaymentFactory;
import com.payment.jorge.paymentbr.model.request.PaymentRequest;
import com.payment.jorge.paymentbr.model.result.PaymentResult;
import com.payment.jorge.paymentbr.service.PaymentProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("visaCheckout")
public class VisaCheckoutPaymentService implements PaymentProcess {

    private PaymentFactory paymentFactory;

    @Autowired
    public VisaCheckoutPaymentService(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        final String status = "Visa Checkout Digital wallet Successfully process a BR payment";
        return paymentFactory.createResult(request, new Random().nextLong(), status);
    }
}

package com.payment.jorge.paymentbr.service.digitalwallet;

import com.payment.jorge.paymentbr.model.factory.PaymentFactory;
import com.payment.jorge.paymentbr.model.request.PaymentRequest;
import com.payment.jorge.paymentbr.model.result.PaymentResult;
import com.payment.jorge.paymentbr.service.PaymentProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("masterPass")
public class MasterPassPaymentService implements PaymentProcess {

    private PaymentFactory paymentFactory;

    @Autowired
    public MasterPassPaymentService(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        final String status = "MasterPass Digital wallet Successfully process BR payment";
        return paymentFactory.createResult(request, new Random().nextLong(), status);
    }

}

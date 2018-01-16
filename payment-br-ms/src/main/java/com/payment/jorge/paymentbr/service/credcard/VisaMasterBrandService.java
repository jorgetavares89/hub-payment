package com.payment.jorge.paymentbr.service.credcard;

import com.payment.jorge.paymentbr.model.factory.PaymentFactory;
import com.payment.jorge.paymentbr.model.request.PaymentRequest;
import com.payment.jorge.paymentbr.model.result.PaymentResult;
import com.payment.jorge.paymentbr.service.PaymentProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("visaMasterBrand")
public class VisaMasterBrandService implements PaymentProcess {

    private PaymentFactory paymentFactory;

    @Autowired
    public VisaMasterBrandService(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        final String status = "Visa/Master Brand Successfully process BR payment";
        return paymentFactory.createResult(request, new Random().nextLong(), status);
    }
}

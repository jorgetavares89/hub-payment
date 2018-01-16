package com.payment.jorge.paymentbr.model.factory;

import com.payment.jorge.paymentbr.model.entity.Payment;
import com.payment.jorge.paymentbr.model.request.PaymentRequest;
import com.payment.jorge.paymentbr.model.result.PaymentResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {

    public Payment create(PaymentRequest request) {
        return new Payment.Builder()
                .withRestaurantId(request.getRestaurantId())
                .withUserId(request.getUserId())
                .withCountryCode(request.getCountryCode())
                .withPaymentBrand(request.getPaymentBrand())
                .withCredCard(request.getCredCard())
                .withDigitalWallet(request.getDigitalWallet())
                .build();
    }

    public PaymentResult createResult(Payment payment, String status) {
        return new PaymentResult.Builder()
                .withId(payment.getId())
                .withRestaurantId(payment.getRestaurantId())
                .withUserId(payment.getUserId())
                .withStatus(status)
                .build();
    }

    public PaymentResult createResult(PaymentRequest payment, Long id,  String status) {
        return new PaymentResult.Builder()
                .withId(id)
                .withRestaurantId(payment.getRestaurantId())
                .withUserId(payment.getUserId())
                .withStatus(status)
                .build();
    }

}

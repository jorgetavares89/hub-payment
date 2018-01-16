package com.payment.jorge.paymentmx.model.factory;

import com.payment.jorge.paymentmx.model.result.PaymentResult;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentFactory {


    public PaymentResult createResult(Long restaurantId, Long userId, String feedBack) {
        return new PaymentResult.Builder()
                .withId(new Random().nextLong())
                .withRestaurantId(restaurantId)
                .withUserId(userId)
                .withStatus(feedBack)
                .build();
    }

}
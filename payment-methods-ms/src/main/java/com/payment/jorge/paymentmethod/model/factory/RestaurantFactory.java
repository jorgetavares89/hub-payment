package com.payment.jorge.paymentmethod.model.factory;

import com.payment.jorge.paymentmethod.model.entity.Restaurant;
import com.payment.jorge.paymentmethod.model.result.RestaurantResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantFactory {
	
	private PaymentMethodFactory paymentMethodFactory;
	
	@Autowired
	public RestaurantFactory(PaymentMethodFactory resourceFactory) {
		this.paymentMethodFactory = resourceFactory;
	}


    public RestaurantResult createResult(Restaurant restaurant) {
        return new RestaurantResult.Builder()
                .withId(restaurant.getId())
                .withName(restaurant.getName())
                .withPaymentMethodsResult(paymentMethodFactory.createPaymentMethodResultList(restaurant.getPaymentMethods()))
                .build();
    }


}
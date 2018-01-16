package com.payment.jorge.paymentmethod.model.factory;

import com.payment.jorge.paymentmethod.model.entity.PaymentMethod;
import com.payment.jorge.paymentmethod.model.result.PaymentMethodResult;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class PaymentMethodFactory {
	
	public PaymentMethodResult createResult(PaymentMethod paymentMethod) {
		return new PaymentMethodResult.Builder()
				                      .withId(paymentMethod.getId())
                                      .withName(paymentMethod.getName())
                                      .withType(paymentMethod.getType())
				                      .build();
	}

	public List<PaymentMethodResult> createPaymentMethodResultList(List<PaymentMethod> paymentMethods) {
		return paymentMethods.stream()
				             .map(this::createResult)
				             .collect(toList());
	}
}

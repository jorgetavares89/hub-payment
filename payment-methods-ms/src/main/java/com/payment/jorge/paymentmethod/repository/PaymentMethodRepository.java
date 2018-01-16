package com.payment.jorge.paymentmethod.repository;

import com.payment.jorge.paymentmethod.model.entity.PaymentMethod;
import com.payment.jorge.paymentmethod.model.entity.PaymentMethodType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {
    List<PaymentMethod> findByType(PaymentMethodType type);
}

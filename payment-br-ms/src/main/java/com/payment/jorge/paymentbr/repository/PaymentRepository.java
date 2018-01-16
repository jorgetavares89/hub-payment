package com.payment.jorge.paymentbr.repository;

import com.payment.jorge.paymentbr.model.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long>{
}

package com.payment.jorge.paymentmethod.service;

import com.google.common.collect.Lists;
import com.payment.jorge.paymentmethod.exception.NotFoundException;
import com.payment.jorge.paymentmethod.model.entity.PaymentMethod;
import com.payment.jorge.paymentmethod.model.entity.PaymentMethodType;
import com.payment.jorge.paymentmethod.model.factory.PaymentMethodFactory;
import com.payment.jorge.paymentmethod.model.result.PaymentMethodResult;
import com.payment.jorge.paymentmethod.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private PaymentMethodRepository repository;
    private PaymentMethodFactory factory;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository repository,
                                PaymentMethodFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public PaymentMethodResult save(PaymentMethod paymentMethod) {
        return factory.createResult(repository.save(paymentMethod));
    }

    public Iterable<PaymentMethod> save(List<PaymentMethod> paymentMethods) {
        return repository.save(paymentMethods);
    }

    public List<PaymentMethod> findByType(PaymentMethodType type) {
        List<PaymentMethod> methods = repository.findByType(type);
        if (methods.isEmpty()) throw new NotFoundException("Not found for this type");
        return methods;
    }

    public List<PaymentMethod> findAll() {
        List<PaymentMethod> methods = Lists.newArrayList(repository.findAll());
        if (methods.isEmpty()) throw new NotFoundException("Not found for this type");
        return methods;
    }

}

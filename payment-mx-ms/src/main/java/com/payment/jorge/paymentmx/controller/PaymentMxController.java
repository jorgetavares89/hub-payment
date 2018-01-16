package com.payment.jorge.paymentmx.controller;

import com.payment.jorge.paymentmx.model.request.PaymentRequest;
import com.payment.jorge.paymentmx.model.result.PaymentResult;
import com.payment.jorge.paymentmx.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PaymentMxController {

    private PaymentService service;

    @Autowired
    public PaymentMxController(PaymentService service) {
        this.service = service;
    }

    @PostMapping(value = "/restaurant/{id}")
    public ResponseEntity<PaymentResult> doPayment(@PathVariable Long id,
                                                   @Valid @RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.process(paymentRequest));
    }
}

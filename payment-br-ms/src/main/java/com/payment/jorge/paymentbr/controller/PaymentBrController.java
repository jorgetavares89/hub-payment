package com.payment.jorge.paymentbr.controller;

import com.payment.jorge.paymentbr.model.request.PaymentRequest;
import com.payment.jorge.paymentbr.model.result.PaymentResult;
import com.payment.jorge.paymentbr.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PaymentBrController {

    private PaymentService service;

    @Autowired
    public PaymentBrController(PaymentService service) {
        this.service = service;
    }

    @PostMapping(value = "/restaurant/{id}")
    public ResponseEntity<PaymentResult> doPayment(@PathVariable Long id,
                                                   @Valid @RequestBody PaymentRequest paymentRequest) {
        final PaymentResult result = service.process(paymentRequest, id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }
}

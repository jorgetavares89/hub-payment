package com.payment.jorge.paymentmethod.controller;

import com.payment.jorge.paymentmethod.service.RestaurantService;
import com.payment.jorge.paymentmethod.model.result.RestaurantResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/methods")
public class PaymentMethodController {

    private final RestaurantService service;
    
    @Autowired
    public PaymentMethodController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping(value = "/restaurant/{id}")
    public RestaurantResult getByRestaurant (@PathVariable Long id) {
        return service.findById(id);
    }

}

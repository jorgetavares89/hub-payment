package com.payment.jorge.paymentmethod.service;

import com.payment.jorge.paymentmethod.exception.NotFoundException;
import com.payment.jorge.paymentmethod.model.entity.Restaurant;
import com.payment.jorge.paymentmethod.model.factory.RestaurantFactory;
import com.payment.jorge.paymentmethod.repository.RestaurantRepository;
import com.payment.jorge.paymentmethod.model.result.RestaurantResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

	private RestaurantRepository repository;
	private RestaurantFactory factory;

	@Autowired
	public RestaurantService(RestaurantRepository repository, RestaurantFactory factory) {
		this.repository = repository;
		this.factory = factory;
	}

	public RestaurantResult findById(Long id) {
        return factory.createResult(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found for ["+id+"]")));
	}

	public Restaurant save(Restaurant restaurant) {
	    return repository.save(restaurant);
    }

}
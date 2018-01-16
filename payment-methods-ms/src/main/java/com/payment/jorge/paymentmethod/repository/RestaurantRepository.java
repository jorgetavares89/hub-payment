package com.payment.jorge.paymentmethod.repository;

import com.payment.jorge.paymentmethod.model.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findById(Long id);
}
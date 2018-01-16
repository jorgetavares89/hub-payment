package com.payment.jorge.paymentmethod.model.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class RestaurantResult {
    
    private Long id;
    private String name;
    private List<PaymentMethodResult> paymentMethodResults;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PaymentMethodResult> getPaymentMethods() {
        return paymentMethodResults;
    }

    public void setPaymentMethod(List<PaymentMethodResult> paymentMethodResults) {
        this.paymentMethodResults = paymentMethodResults;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentMethodResult fromJson(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, PaymentMethodResult.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }


    public static class Builder {

        private RestaurantResult restaurantRequest = new RestaurantResult();

        public Builder withId(Long groupId) {
            restaurantRequest.setId(groupId);
            return this;
        }

        public Builder withName(String name) {
            restaurantRequest.setName(name);
            return this;
        }

        public Builder withPaymentMethodsResult(List<PaymentMethodResult> paymentMethodResults) {
            restaurantRequest.setPaymentMethod(paymentMethodResults);
            return this;
        }

        public RestaurantResult build() {
            return this.restaurantRequest;
        }
    }

    @Override
    public String toString() {
        try {
            return toJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

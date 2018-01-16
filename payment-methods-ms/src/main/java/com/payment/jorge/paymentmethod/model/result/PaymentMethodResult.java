package com.payment.jorge.paymentmethod.model.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.jorge.paymentmethod.model.entity.PaymentMethodType;

import java.io.IOException;

public class PaymentMethodResult {

    private Long id;
    private String name;
    private PaymentMethodType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentMethodType getType() {
        return type;
    }

    public void setType(PaymentMethodType type) {
        this.type = type;
    }

    public PaymentMethodResult fromJson(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, PaymentMethodResult.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static class Builder {

        private PaymentMethodResult paymentMethodResult = new PaymentMethodResult();

        public Builder withId(Long groupId) {
            this.paymentMethodResult.setId(groupId);
            return this;
        }

        public Builder withName(String accountId) {
            this.paymentMethodResult.setName(accountId);
            return this;
        }

        public Builder withType(PaymentMethodType type) {
            paymentMethodResult.setType(type);
            return this;
        }

        public PaymentMethodResult build() {
            return this.paymentMethodResult;
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

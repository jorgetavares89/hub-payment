package com.payment.jorge.paymentmx.model.request;



import com.payment.jorge.paymentmx.model.enums.CountryCode;
import com.payment.jorge.paymentmx.model.enums.DigitalWallet;
import com.payment.jorge.paymentmx.model.enums.PaymentBrand;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PaymentRequest {

    private Long restaurantId;
    private Long userId;
    private CountryCode countryCode;
    private PaymentBrand paymentBrand;
    private Long credCard;
    private DigitalWallet digitalWallet;

    @NotNull
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @NotNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public PaymentBrand getPaymentBrand() {
        return paymentBrand;
    }

    public void setPaymentBrand(PaymentBrand paymentBrand) {
        this.paymentBrand = paymentBrand;
    }

    public Long getCredCard() {
        return credCard;
    }

    public void setCredCard(Long credCard) {
        this.credCard = credCard;
    }

    public DigitalWallet getDigitalWallet() {
        return digitalWallet;
    }

    public void setDigitalWallet(DigitalWallet digitalWallet) {
        this.digitalWallet = digitalWallet;
    }

    public static class Builder {

        PaymentRequest paymentRequest = new PaymentRequest();

        public Builder withRestaurantId(Long restaurantId) {
            paymentRequest.setRestaurantId(restaurantId);
            return this;
        }

        public Builder withUserId(Long userId) {
            paymentRequest.setUserId(userId);
            return this;
        }

        public Builder withCountryCode(CountryCode countryCode) {
            paymentRequest.setCountryCode(countryCode);
            return this;
        }

        public Builder withPaymentBrand(PaymentBrand paymentBrand) {
            paymentRequest.setPaymentBrand(paymentBrand);
            return this;
        }

        public Builder withCredCard(Long credCard) {
            paymentRequest.setCredCard(credCard);
            return this;
        }

        public Builder withDigitalWallet(DigitalWallet digitalWallet) {
            paymentRequest.setDigitalWallet(digitalWallet);
            return this;
        }

        public PaymentRequest build(){
            return paymentRequest;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequest that = (PaymentRequest) o;
        return Objects.equals(restaurantId, that.restaurantId) &&
                countryCode == that.countryCode &&
                paymentBrand == that.paymentBrand &&
                Objects.equals(credCard, that.credCard) &&
                digitalWallet == that.digitalWallet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, countryCode, paymentBrand, credCard, digitalWallet);
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "restaurantId=" + restaurantId +
                ", countryCode=" + countryCode +
                ", paymentBrand=" + paymentBrand +
                ", credCard=" + credCard +
                ", digitalWallet=" + digitalWallet +
                '}';
    }
}

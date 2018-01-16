package com.payment.jorge.paymentbr.model.entity;

import com.payment.jorge.paymentbr.model.enums.CountryCode;
import com.payment.jorge.paymentbr.model.enums.DigitalWallet;
import com.payment.jorge.paymentbr.model.enums.PaymentBrand;

import javax.persistence.*;

@Table
@Entity
public class Payment {

    private Long id;
    private Long restaurantId;
    private Long userId;
    private CountryCode countryCode;
    private PaymentBrand paymentBrand;
    private Long credCard;
    private DigitalWallet digitalWallet;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Column(nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Enumerated(value = EnumType.STRING)
    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    @Enumerated(value = EnumType.STRING)
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

        Payment order = new Payment();

        public Builder withRestaurantId(Long restaurantId) {
            order.setRestaurantId(restaurantId);
            return this;
        }

        public Builder withUserId(Long userId) {
            order.setUserId(userId);
            return this;
        }

        public Builder withCountryCode(CountryCode countryCode) {
            order.setCountryCode(countryCode);
            return this;
        }

        public Builder withPaymentBrand(PaymentBrand paymentBrand) {
            order.setPaymentBrand(paymentBrand);
            return this;
        }

        public Builder withCredCard(Long credCard) {
            order.setCredCard(credCard);
            return this;
        }

        public Builder withDigitalWallet(DigitalWallet digitalWallet) {
            order.setDigitalWallet(digitalWallet);
            return this;
        }

        public Payment build() {
            return order;
        }
    }
}

package com.payment.jorge.paymentmx.model.result;

import java.util.Objects;

public class PaymentResult {

    private Long id;
    private Long restaurantId;
    private Long userId;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Builder {

        PaymentResult paymentResult = new PaymentResult();

        public Builder withId(Long id) {
            paymentResult.setId(id);
            return this;
        }

        public Builder withRestaurantId(Long restaurantId) {
            paymentResult.setRestaurantId(restaurantId);
            return this;
        }

        public Builder withUserId(Long userId) {
            paymentResult.setUserId(userId);
            return this;
        }

        public Builder withStatus(String status) {
            paymentResult.setStatus(status);
            return this;
        }

        public PaymentResult build(){
            return paymentResult;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentResult that = (PaymentResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, restaurantId, userId, status);
    }

    @Override
    public String toString() {
        return "PaymentResult{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                '}';
    }
}

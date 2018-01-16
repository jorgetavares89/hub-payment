package com.payment.jorge.paymentmethod.model.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Table
@Entity
public class Restaurant {

	private Long id;
    private String name;
    private List<PaymentMethod> paymentMethods;

    public Restaurant() {
    	paymentMethods = new ArrayList<>();
    }
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "restaurant_payment_method",
               joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "payment_method_id", referencedColumnName = "id"))
	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	
	public void addPaymentMethod(PaymentMethod paymentMethod) {
		paymentMethods.add(paymentMethod);
	}

	public Restaurant fromJson(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, Restaurant.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

	public static class Builder {

        private Restaurant restaurant = new Restaurant();
        
        public Builder withName(String name) {
            restaurant.setName(name);
        	return this;
        }

        public Builder withPaymentMethods(List<PaymentMethod> paymentMethods) {
            restaurant.setPaymentMethods(paymentMethods);
        	return this;
        }
        
        public Restaurant build() {
            return restaurant;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(paymentMethods, that.paymentMethods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, paymentMethods);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
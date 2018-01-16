package com.payment.jorge.paymentmethod.model.entity;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Table
@Entity
public class PaymentMethod {
	
	private Long id;
	private String name;
	private PaymentMethodType type;
	private List<Restaurant> restaurants;
	
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
	
    @ManyToMany(mappedBy = "paymentMethods")
    public List<Restaurant> getRestaurants() {
		return restaurants;
	}
    
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public PaymentMethod fromJson(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, PaymentMethod.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    @Enumerated(value = EnumType.STRING)
    public PaymentMethodType getType() {
        return type;
    }

    public void setType(PaymentMethodType type) {
        this.type = type;
    }

    public static class Builder {

        private PaymentMethod paymentMethod = new PaymentMethod();

        public Builder withName(String name) {
            this.paymentMethod.setName(name);
            return this;
        }

        public Builder withType(PaymentMethodType type) {
            paymentMethod.setType(type);
            return this;
        }
                
        public PaymentMethod build() {
            return this.paymentMethod;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(restaurants, that.restaurants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, restaurants);
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}

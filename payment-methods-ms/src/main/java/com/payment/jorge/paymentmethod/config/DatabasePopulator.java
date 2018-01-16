package com.payment.jorge.paymentmethod.config;

import com.payment.jorge.paymentmethod.model.entity.PaymentMethod;
import com.payment.jorge.paymentmethod.model.entity.PaymentMethodType;
import com.payment.jorge.paymentmethod.model.entity.Restaurant;
import com.payment.jorge.paymentmethod.service.PaymentMethodService;
import com.payment.jorge.paymentmethod.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabasePopulator implements ApplicationRunner {

    private RestaurantService restaurantService;
    private PaymentMethodService paymentMethodService;

    @Autowired
    public DatabasePopulator(RestaurantService restaurantService,
                             PaymentMethodService paymentMethodService) {
        this.restaurantService = restaurantService;
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createOnlineMethods();
        createOfflineMethods();
        createDummyRestaurants();
    }

    private void createDummyRestaurants() {
        Restaurant moquecasHouse = createRestaurant("Casa da Moqueca");
        createRestaurantByPaymentMethodType(moquecasHouse, PaymentMethodType.OFFLINE);
        Restaurant bigJack = createRestaurant("Big Jack");
        createRestaurantByPaymentMethodType(bigJack, PaymentMethodType.ONLINE);
        Restaurant pizzaHut = createRestaurant("Pizza Hut");
        createRestaurantByPaymentMethodType(pizzaHut, PaymentMethodType.ALL);
        Restaurant outback = createRestaurant("Outback");
        createRestaurantByPaymentMethodType(outback, PaymentMethodType.ONLINE);
    }

    private Restaurant createRestaurant(String name) {
        return new Restaurant.Builder()
                .withName(name)
                .build();
    }

    private void createRestaurantByPaymentMethodType(Restaurant restaurant, PaymentMethodType methodType) {
        List<PaymentMethod> methods = getPaymentMethodsByType(methodType);
        restaurant.setPaymentMethods(methods);
        restaurantService.save(restaurant);
    }

    private List<PaymentMethod> getPaymentMethodsByType(PaymentMethodType methodType) {
        List<PaymentMethod> methods;
        if (methodType.equals(PaymentMethodType.ALL)) {
            methods = paymentMethodService.findAll();
        } else {
            methods = paymentMethodService.findByType(methodType);
        }
        return methods;
    }

    private void createOfflineMethods() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(createPaymentMethod("Check", PaymentMethodType.OFFLINE));
        paymentMethods.add(createPaymentMethod("POS Machine", PaymentMethodType.OFFLINE));
        paymentMethods.add(createPaymentMethod("Cash", PaymentMethodType.OFFLINE));
        paymentMethodService.save(paymentMethods);
    }

    private void createOnlineMethods() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(createPaymentMethod("Cred Card", PaymentMethodType.ONLINE));
        paymentMethods.add(createPaymentMethod("Digital Wallet", PaymentMethodType.ONLINE));
        paymentMethodService.save(paymentMethods);
    }

    private PaymentMethod createPaymentMethod(String name, PaymentMethodType type) {
        return new PaymentMethod.Builder()
                .withName(name)
                .withType(type)
                .build();
    }


}

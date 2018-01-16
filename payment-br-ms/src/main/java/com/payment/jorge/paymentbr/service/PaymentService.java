package com.payment.jorge.paymentbr.service;

import com.payment.jorge.paymentbr.exception.BadRequestException;
import com.payment.jorge.paymentbr.model.entity.Payment;
import com.payment.jorge.paymentbr.model.enums.CountryCode;
import com.payment.jorge.paymentbr.model.factory.PaymentFactory;
import com.payment.jorge.paymentbr.model.request.PaymentRequest;
import com.payment.jorge.paymentbr.model.result.PaymentResult;
import com.payment.jorge.paymentbr.repository.PaymentRepository;
import com.payment.jorge.paymentbr.model.enums.PaymentBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository repository;
    private PaymentFactory factory;
    private PaymentProcess visaMasterService;
    private PaymentProcess otherBrandService;
    private PaymentProcess googlePaymentService;
    private PaymentProcess visaCheckoutService;
    private PaymentProcess masterPassService;

    @Autowired
    public PaymentService(PaymentRepository repository,
                          PaymentFactory factory,
                          @Qualifier("visaMasterBrand") PaymentProcess visaMasterService,
                          @Qualifier("otherBrand") PaymentProcess otherBrandPaymentService,
                          @Qualifier("google") PaymentProcess googlePaymentService,
                          @Qualifier("visaCheckout") PaymentProcess visaCheckoutService,
                          @Qualifier("masterPass") PaymentProcess masterPassService) {
        this.repository = repository;
        this.factory = factory;
        this.visaMasterService = visaMasterService;
        this.otherBrandService = otherBrandPaymentService;
        this.googlePaymentService = googlePaymentService;
        this.visaCheckoutService = visaCheckoutService;
        this.masterPassService = masterPassService;
    }

    public PaymentResult process (PaymentRequest request, Long restaurantId) {
        PaymentResult paymentResult = null;
        if (request.getCountryCode().equals(CountryCode.BR)) {
            paymentResult = processBrPayment(request, restaurantId);
        } else {
            throwBadRequest("Invalid Country");
        }
        return paymentResult;
    }

    private PaymentResult processBrPayment(PaymentRequest request, Long restaurantId) {
        PaymentResult paymentResult = null;
        if (request.getDigitalWallet() != null && request.getPaymentBrand() != null) {
            throwBadRequest("Select just one payment form");
        } else if (request.getDigitalWallet() != null) {
            paymentResult = processDigitalWalletPayment(request);
        } else if (request.getCredCard() != null) {
            paymentResult = processCredCardPayment(request);
        } else {
            throwBadRequest("Invalid params");
        }
        save(request, restaurantId);
        return paymentResult;
    }

    private void throwBadRequest(String message) {
        throw new BadRequestException(message);
    }

    private void save(PaymentRequest request, Long restaurantId) {
        request.setRestaurantId(restaurantId);
        final Payment payment = factory.create(request);
        repository.save(payment);
    }

    private PaymentResult processCredCardPayment(PaymentRequest request) {
        PaymentResult result;
        if (request.getPaymentBrand().equals(PaymentBrand.MASTERCARD) || request.getPaymentBrand().equals(PaymentBrand.VISA)) {
            result = visaMasterService.process(request);
        } else {
            result = otherBrandService.process(request);
        }
        return result;
    }

    private PaymentResult processDigitalWalletPayment(PaymentRequest request) {
        PaymentResult result = null;
        switch (request.getDigitalWallet()) {
            case VISA_CHECKOUT:
                result = visaCheckoutService.process(request);
                break;
            case GOOGLE:
                result = googlePaymentService.process(request);
                break;
            case MASTER_PASS:
                result = masterPassService.process(request);
                break;
        }
        return result;
    }
}

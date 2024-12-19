package com.mygitgor.restaurant.service;

import com.mygitgor.restaurant.domain.Order;
import com.mygitgor.restaurant.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createStripePaymentLink(Order order)throws Exception;
    PaymentResponse createIdramPaymentLink(Order order) throws Exception;
    PaymentResponse createEasyPayPaymentLink(Order order) throws Exception;
}

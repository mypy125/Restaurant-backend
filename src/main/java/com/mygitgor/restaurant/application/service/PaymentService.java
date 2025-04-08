package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.OrderEntity;
import com.mygitgor.restaurant.api.controller.DTOs.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createStripePaymentLink(OrderEntity order)throws Exception;
    PaymentResponse createIdramPaymentLink(OrderEntity order) throws Exception;
    PaymentResponse createEasyPayPaymentLink(OrderEntity order) throws Exception;
}

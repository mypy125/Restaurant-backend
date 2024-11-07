package com.mygitgor.restaurant.service;

import com.mygitgor.restaurant.domain.Order;
import com.mygitgor.restaurant.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPaymentLink(Order order)throws Exception;
}

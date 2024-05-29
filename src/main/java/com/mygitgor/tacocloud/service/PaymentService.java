package com.mygitgor.tacocloud.service;

import com.mygitgor.tacocloud.domain.Order;
import com.mygitgor.tacocloud.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPaymentLink(Order order)throws Exception;
}

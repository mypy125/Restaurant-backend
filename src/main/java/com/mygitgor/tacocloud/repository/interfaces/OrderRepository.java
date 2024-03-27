package com.mygitgor.tacocloud.repository.interfaces;

import com.mygitgor.tacocloud.domain.taco.Order;

public interface OrderRepository {
    Order save(Order order);
}

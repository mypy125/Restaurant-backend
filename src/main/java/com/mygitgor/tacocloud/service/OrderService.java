package com.mygitgor.tacocloud.service;

import com.mygitgor.tacocloud.domain.Order;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest request, User user) throws Exception;
    Order updateOrder(Long orderId, String orderStatus)throws Exception;
    void calcelOrder(Long orderId)throws Exception;
    List<Order> getUsersOrder(Long userId)throws Exception;
    List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus)throws Exception;
    Order findOrderById(Long orderId)throws Exception;

}

package com.mygitgor.restaurant.service;

import com.mygitgor.restaurant.domain.Order;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest request, User user) throws Exception;
    Order updateOrder(Long orderId, String orderStatus)throws Exception;
    void calcelOrder(Long orderId)throws Exception;
    List<Order> getUsersOrder(Long userId)throws Exception;
    List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus)throws Exception;
    Order findOrderById(Long orderId)throws Exception;

}

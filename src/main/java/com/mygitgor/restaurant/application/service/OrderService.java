package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.OrderEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.request.OrderRequest;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderRequest request, UserEntity user) throws Exception;
    OrderEntity updateOrder(Long orderId, String orderStatus)throws Exception;
    void cancelOrder(Long orderId)throws Exception;
    List<OrderEntity> getUsersOrder(Long userId)throws Exception;
    List<OrderEntity> getRestaurantsOrder(Long restaurantId, String orderStatus)throws Exception;
    OrderEntity findOrderById(Long orderId)throws Exception;

}

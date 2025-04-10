package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.RestaurantDto;
import com.mygitgor.restaurant.api.controller.DTOs.request.CreateRestaurantRequest;
import com.mygitgor.restaurant.model.domain.Restaurant;
import com.mygitgor.restaurant.model.domain.User;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(CreateRestaurantRequest request, User user);
    Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant)throws Exception;
    void deleteRestaurant(Long restaurantId)throws Exception;
    List<Restaurant> getAllRestaurant();
    List<Restaurant> searchRestaurant(String keyword);
    Restaurant findRestaurantById(Long id)throws Exception;
    Restaurant findRestaurantByUserId(Long id)throws Exception;
    RestaurantDto addToFavorites(Long restaurantId, User user)throws Exception;
    Restaurant updateRestaurantStatus(Long id)throws Exception;
    void removeFromFavorites(Long restaurantId, User user)throws Exception;
}

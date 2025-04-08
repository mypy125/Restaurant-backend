package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.RestaurantDto;
import com.mygitgor.restaurant.api.controller.DTOs.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    RestaurantEntity createRestaurant(CreateRestaurantRequest request, UserEntity user);
    RestaurantEntity updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant)throws Exception;
    void deleteRestaurant(Long restaurantId)throws Exception;
    List<RestaurantEntity> getAllRestaurant();
    List<RestaurantEntity> searchRestaurant(String keyword);
    RestaurantEntity findRestaurantById(Long id)throws Exception;
    RestaurantEntity findRestaurantByUserId(Long id)throws Exception;
    RestaurantDto addToFavorites(Long restaurantId, UserEntity user)throws Exception;
    RestaurantEntity updateRestaurantStatus(Long id)throws Exception;
    void removeFromFavorites(Long restaurantId, UserEntity user)throws Exception;

}

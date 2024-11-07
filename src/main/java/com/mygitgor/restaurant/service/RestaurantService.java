package com.mygitgor.restaurant.service;

import com.mygitgor.restaurant.domain.Restaurant;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.dto.RestaurantDto;
import com.mygitgor.restaurant.request.CreateRestaurantRequest;

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

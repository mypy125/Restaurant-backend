package com.mygitgor.tacocloud.service;

import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.dto.RestaurantDto;
import com.mygitgor.tacocloud.request.CreateRestaurantRequest;
import lombok.SneakyThrows;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(CreateRestaurantRequest request, User user);
    @SneakyThrows
    Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant);
    @SneakyThrows
    void deleteRestaurant(Long restaurantId);
    List<Restaurant> getAllRestaurant();
    List<Restaurant> searchRestaurant();
    @SneakyThrows
    Restaurant findRestaurantById(Long id);
    @SneakyThrows
    Restaurant findRestaurantByUserId(Long id);
    @SneakyThrows
    RestaurantDto addToFavorites(Long restaurantId, User user);
    @SneakyThrows
    Restaurant updateRestaurantStatus(Long id);


}

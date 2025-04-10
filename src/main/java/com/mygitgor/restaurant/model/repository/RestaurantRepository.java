package com.mygitgor.restaurant.model.repository;

import com.mygitgor.restaurant.model.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository  {
    Restaurant save(Restaurant restaurant);
    void delete(Restaurant restaurant);
    Optional<Restaurant> findById(Long id);
    Optional<Restaurant> findByOwnerId(Long ownerId);
    List<Restaurant> findAll();
    List<Restaurant> findBySearchQuery(String keyword);
}

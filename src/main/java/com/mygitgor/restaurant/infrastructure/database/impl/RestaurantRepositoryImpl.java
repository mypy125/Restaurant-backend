package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.infrastructure.database.jpa.RestaurantJpaRepository;
import com.mygitgor.restaurant.mapper.RestaurantMapper;
import com.mygitgor.restaurant.model.domain.Restaurant;
import com.mygitgor.restaurant.model.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private final RestaurantJpaRepository jpaRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity entity = restaurantMapper.toEntity(restaurant);
        RestaurantEntity saved = jpaRepository.save(entity);
        return restaurantMapper.toDomain(saved);
    }

    @Override
    public void delete(Restaurant restaurant) {
        jpaRepository.delete(restaurantMapper.toEntity(restaurant));
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return jpaRepository.findById(id).map(restaurantMapper::toDomain);
    }

    @Override
    public Optional<Restaurant> findByOwnerId(Long ownerId) {
        return jpaRepository.findByOwnerId(ownerId).map(restaurantMapper::toDomain);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantMapper.toDomain(jpaRepository.findAll());
    }

    @Override
    public List<Restaurant> findBySearchQuery(String keyword) {
        return restaurantMapper.toDomain(jpaRepository.findBySearchQuery(keyword));
    }
}

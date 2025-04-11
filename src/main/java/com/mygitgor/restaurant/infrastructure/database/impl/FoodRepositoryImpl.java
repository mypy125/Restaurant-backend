package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.FoodEntity;
import com.mygitgor.restaurant.infrastructure.database.jpa.FoodJpaRepository;
import com.mygitgor.restaurant.mapper.FoodMapper;
import com.mygitgor.restaurant.model.domain.Food;
import com.mygitgor.restaurant.model.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepository {
    private final FoodJpaRepository jpaRepository;
    private final FoodMapper foodMapper;

    @Override
    public Food save(Food food) {
        FoodEntity saved = jpaRepository.save(foodMapper.toEntity(food));
        return foodMapper.toDomain(saved);
    }

    @Override
    public Optional<Food> findById(Long id) {
        return jpaRepository.findById(id)
                .map(foodMapper::toDomain);
    }

    @Override
    public List<Food> findByRestaurantId(Long restaurantId) {
        return jpaRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return jpaRepository.searchFood(keyword)
                .stream()
                .map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Food food) {
        jpaRepository.delete(foodMapper.toEntity(food));
    }
}

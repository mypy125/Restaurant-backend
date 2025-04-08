package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.jpa.FoodJpaRepository;
import com.mygitgor.restaurant.mapper.FoodMapper;
import com.mygitgor.restaurant.model.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepository {
    private final FoodJpaRepository jpaRepository;
    private final FoodMapper foodMapper;
}

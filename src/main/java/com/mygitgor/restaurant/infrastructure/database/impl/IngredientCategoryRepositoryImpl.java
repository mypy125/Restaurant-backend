package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.IngredientCategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.jpa.IngredientCategoryJpaRepository;
import com.mygitgor.restaurant.mapper.IngredientCategoryMapper;
import com.mygitgor.restaurant.model.domain.IngredientCategory;
import com.mygitgor.restaurant.model.repository.IngredientCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class IngredientCategoryRepositoryImpl implements IngredientCategoryRepository {
    private final IngredientCategoryJpaRepository jpaRepository;
    private final IngredientCategoryMapper ingredientCategoryMapper;

    @Override
    public IngredientCategory save(IngredientCategory category) {
        IngredientCategoryEntity entity = ingredientCategoryMapper.toEntity(category);
        return ingredientCategoryMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<IngredientCategory> findById(Long id) {
        return jpaRepository.findById(id).map(ingredientCategoryMapper::toDomain);
    }

    @Override
    public List<IngredientCategory> findByRestaurantId(Long restaurantId) {
        return jpaRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(ingredientCategoryMapper::toDomain)
                .collect(Collectors.toList());
    }
}

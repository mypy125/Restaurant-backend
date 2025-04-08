package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.jpa.IngredientCategoryJpaRepository;
import com.mygitgor.restaurant.mapper.IngredientCategoryMapper;
import com.mygitgor.restaurant.model.repository.IngredientCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IngredientCategoryRepositoryImpl implements IngredientCategoryRepository {
    private final IngredientCategoryJpaRepository jpaRepository;
    private final IngredientCategoryMapper ingredientCategoryMapper;
}

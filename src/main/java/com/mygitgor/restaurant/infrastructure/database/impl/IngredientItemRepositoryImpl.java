package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.jpa.IngredientItemJpaRepository;
import com.mygitgor.restaurant.mapper.IngredientItemMapper;
import com.mygitgor.restaurant.model.repository.IngredientItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IngredientItemRepositoryImpl implements IngredientItemRepository {
    private final IngredientItemJpaRepository jpaRepository;
    private final IngredientItemMapper ingredientItemMapper;
}

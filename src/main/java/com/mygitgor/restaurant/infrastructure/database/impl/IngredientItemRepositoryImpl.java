package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.IngredientItemEntity;
import com.mygitgor.restaurant.infrastructure.database.jpa.IngredientItemJpaRepository;
import com.mygitgor.restaurant.mapper.IngredientItemMapper;
import com.mygitgor.restaurant.model.domain.IngredientItem;
import com.mygitgor.restaurant.model.repository.IngredientItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class IngredientItemRepositoryImpl implements IngredientItemRepository {
    private final IngredientItemJpaRepository jpaRepository;
    private final IngredientItemMapper ingredientItemMapper;

    @Override
    public IngredientItem save(IngredientItem item) {
        IngredientItemEntity entity = ingredientItemMapper.toEntity(item);
        return ingredientItemMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<IngredientItem> findById(Long id) {
        return jpaRepository.findById(id).map(ingredientItemMapper::toDomain);
    }

    @Override
    public List<IngredientItem> findByRestaurantId(Long restaurantId) {
        return jpaRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(ingredientItemMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<IngredientItem> findAllById(List<Long> ids) {
        return jpaRepository.findAllById(ids)
                .stream()
                .map(ingredientItemMapper::toDomain)
                .collect(Collectors.toList());
    }
}

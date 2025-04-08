package com.mygitgor.restaurant.infrastructure.database.jpa;

import com.mygitgor.restaurant.infrastructure.database.entity.IngredientCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryJpaRepository extends JpaRepository<IngredientCategoryEntity, Long> {
    List<IngredientCategoryEntity> findByRestaurantId(Long id);
}

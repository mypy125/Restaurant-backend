package com.mygitgor.restaurant.infrastructure.database.jpa;

import com.mygitgor.restaurant.infrastructure.database.entity.IngredientItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemJpaRepository extends JpaRepository<IngredientItemEntity, Long> {
    List<IngredientItemEntity> findByRestaurantId(Long id);
}

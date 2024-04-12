package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.IngredientItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientItem, Long> {
    List<IngredientItem> findByRestaurantId(Long id);
}

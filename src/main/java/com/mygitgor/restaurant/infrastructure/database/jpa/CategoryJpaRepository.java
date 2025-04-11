package com.mygitgor.restaurant.infrastructure.database.jpa;

import com.mygitgor.restaurant.infrastructure.database.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByRestaurantId(Long id);
    Optional<CategoryEntity> findByName(String name);
}

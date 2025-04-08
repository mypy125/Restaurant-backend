package com.mygitgor.restaurant.infrastructure.database.jpa;

import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findByCustomerId(Long userId);
}

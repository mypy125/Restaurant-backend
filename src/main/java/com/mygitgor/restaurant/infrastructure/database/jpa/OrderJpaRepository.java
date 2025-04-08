package com.mygitgor.restaurant.infrastructure.database.jpa;

import com.mygitgor.restaurant.infrastructure.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository  extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomerId(Long userId);
    List<OrderEntity> findByRestaurantId(Long restaurantId);
}

package com.mygitgor.restaurant.repository;

import com.mygitgor.restaurant.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long userId);
    List<Order> findByRestaurantId(Long restaurantId);
}

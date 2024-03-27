package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.taco.Order;
import com.mygitgor.tacocloud.domain.taco.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface OrderRepositoryJpa extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}

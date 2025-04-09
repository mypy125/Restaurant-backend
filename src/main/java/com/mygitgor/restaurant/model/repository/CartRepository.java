package com.mygitgor.restaurant.model.repository;

import com.mygitgor.restaurant.model.domain.Cart;

import java.util.Optional;

public interface CartRepository {
    Cart findByCustomerId(Long userId);
    Optional<Cart> findById(Long id);
    Cart save(Cart cart);
}

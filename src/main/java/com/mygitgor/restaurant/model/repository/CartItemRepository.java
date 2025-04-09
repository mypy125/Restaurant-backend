package com.mygitgor.restaurant.model.repository;

import com.mygitgor.restaurant.model.domain.CartItem;

import java.util.Optional;

public interface CartItemRepository{
    Optional<CartItem> findById(Long id);
    CartItem save(CartItem cartItem);
}

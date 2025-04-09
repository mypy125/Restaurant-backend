package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.CartItemEntity;
import com.mygitgor.restaurant.infrastructure.database.jpa.CartItemJpaRepository;
import com.mygitgor.restaurant.mapper.CartItemMapper;
import com.mygitgor.restaurant.model.domain.CartItem;
import com.mygitgor.restaurant.model.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {
    private final CartItemJpaRepository jpaRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public Optional<CartItem> findById(Long id) {
        return jpaRepository.findById(id)
                .map(cartItemMapper::toDomain);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        CartItemEntity entity = cartItemMapper.toEntity(cartItem);
        CartItemEntity saved = jpaRepository.save(entity);
        return cartItemMapper.toDomain(saved);
    }
}

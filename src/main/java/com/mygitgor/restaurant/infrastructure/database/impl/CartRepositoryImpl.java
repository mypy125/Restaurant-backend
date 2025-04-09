package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import com.mygitgor.restaurant.infrastructure.database.jpa.CartJpaRepository;
import com.mygitgor.restaurant.mapper.CartMapper;
import com.mygitgor.restaurant.model.domain.Cart;
import com.mygitgor.restaurant.model.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    private final CartJpaRepository jpaRepository;
    private final CartMapper cartMapper;

    @Override
    public Cart findByCustomerId(Long userId) {
        CartEntity entity = jpaRepository.findByCustomerId(userId);
        return cartMapper.toDomain(entity);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return jpaRepository.findById(id)
                .map(cartMapper::toDomain);
    }

    @Override
    public Cart save(Cart cart) {
        CartEntity savedEntity = jpaRepository.save(cartMapper.toEntity(cart));
        return cartMapper.toDomain(savedEntity);
    }
}

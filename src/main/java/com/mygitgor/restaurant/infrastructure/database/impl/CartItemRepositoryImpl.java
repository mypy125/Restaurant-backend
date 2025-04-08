package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.jpa.CartItemJpaRepository;
import com.mygitgor.restaurant.mapper.CartItemMapper;
import com.mygitgor.restaurant.model.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {
    private final CartItemJpaRepository jpaRepository;
    private final CartItemMapper cartItemMapper;
}

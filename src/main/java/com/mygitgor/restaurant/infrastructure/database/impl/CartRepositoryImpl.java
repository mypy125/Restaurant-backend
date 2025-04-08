package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.jpa.CartJpaRepository;
import com.mygitgor.restaurant.mapper.CartMapper;
import com.mygitgor.restaurant.model.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    private final CartJpaRepository jpaRepository;
    private final CartMapper cartMapper;
}

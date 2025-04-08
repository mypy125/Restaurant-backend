package com.mygitgor.restaurant.infrastructure.database.impl;

import com.mygitgor.restaurant.infrastructure.database.jpa.OrderItemJpaRepository;
import com.mygitgor.restaurant.mapper.OrderItemMapper;
import com.mygitgor.restaurant.model.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private final OrderItemJpaRepository jpaRepository;
    private final OrderItemMapper orderItemMapper;
}

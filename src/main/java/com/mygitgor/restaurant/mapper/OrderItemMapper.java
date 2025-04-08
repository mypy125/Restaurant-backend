package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.OrderItemEntity;
import com.mygitgor.restaurant.model.domain.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends ConfigMapper<OrderItem, OrderItemEntity> {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);
}

package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.OrderEntity;
import com.mygitgor.restaurant.model.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper extends ConfigMapper<Order, OrderEntity> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
}

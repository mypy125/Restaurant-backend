package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartItemMapper extends ConfigMapper<CartItemEntity, CartItemEntity> {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);
}

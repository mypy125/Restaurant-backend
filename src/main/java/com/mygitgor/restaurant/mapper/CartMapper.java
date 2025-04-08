package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import com.mygitgor.restaurant.model.domain.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper extends ConfigMapper<Cart, CartEntity> {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
}

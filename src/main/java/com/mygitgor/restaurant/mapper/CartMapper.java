package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.model.domain.Cart;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper{
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "items", source = "item")
    Cart toDomain(CartEntity entity);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "item", source = "items")
    CartEntity toEntity(Cart model);

    @AfterMapping
    default void afterToEntity(@MappingTarget CartEntity entity, Cart model) {
        if (model.getCustomerId() != null) {
            UserEntity user = new UserEntity();
            user.setId(model.getCustomerId());
            entity.setCustomer(user);
        }
        if (entity.getItem() != null) {
            entity.getItem().forEach(item -> item.setCart(entity));
        }
    }
}

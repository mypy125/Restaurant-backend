package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.CartItemEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.FoodEntity;
import com.mygitgor.restaurant.model.domain.CartItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = FoodMapper.class)
public interface CartItemMapper {
    @Mapping(target = "cartId", source = "cart.id")
    @Mapping(target = "foodId", source = "food.id")
    CartItem toDomain(CartItemEntity entity);

    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "food", ignore = true)
    CartItemEntity toEntity(CartItem model);

    @AfterMapping
    default void afterToEntity(@MappingTarget CartItemEntity entity, CartItem model) {
        if (model.getCartId() != null) {
            CartEntity cart = new CartEntity();
            cart.setId(model.getCartId());
            entity.setCart(cart);
        }
        if (model.getFoodId() != null) {
            FoodEntity food = new FoodEntity();
            food.setId(model.getFoodId());
            entity.setFood(food);
        }
    }
}

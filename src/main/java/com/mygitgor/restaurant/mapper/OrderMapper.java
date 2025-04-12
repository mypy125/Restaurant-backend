package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring",uses = {
        AddressMapper.class
},
        imports = {Date.class
})
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", source = "user")
    @Mapping(target = "createAt", expression = "java(new Date())")
    @Mapping(target = "orderStatus", constant = "PENDING")
    @Mapping(target = "deliveryAddress", source = "savedAddress")
    @Mapping(target = "restaurant", source = "restaurant")
    @Mapping(target = "items", source = "orderItems")
    @Mapping(target = "totalPrice", source = "totalPrice")
    Order toOrder(User user, Address savedAddress, Restaurant restaurant,
                  List<OrderItem> orderItems, Long totalPrice);

    @Mapping(target = "food", source = "cartItem.food")
    @Mapping(target = "ingredients", source = "cartItem.ingredients")
    @Mapping(target = "quantity", source = "cartItem.quantity")
    @Mapping(target = "totalPrice", source = "cartItem.totalPrice")
    OrderItem toOrderItem(CartItem cartItem);

    List<OrderItem> toOrderItems(List<CartItem> cartItems);
}

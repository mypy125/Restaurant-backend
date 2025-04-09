package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.CartItemEntity;
import com.mygitgor.restaurant.api.exceprion.cartexception.CartItemNotFoundException;
import com.mygitgor.restaurant.api.controller.DTOs.request.AddCartItemRequest;
import com.mygitgor.restaurant.model.domain.Cart;
import com.mygitgor.restaurant.model.domain.CartItem;

public interface CartService {
    CartItem addItemToCart(AddCartItemRequest request, String jwt)throws Exception;
    CartItem updateCartItemQuantity(Long cartItemId, int quantity)throws CartItemNotFoundException;
    Cart removeItemFromCart(Long id, String jwt)throws Exception;
    Long calculateCartTotals(Cart cart)throws Exception;
    Cart findCartById(Long id)throws Exception;
    Cart findCartByUserId(Long userId)throws Exception;
    Cart clearCart(Long userId)throws Exception;
}

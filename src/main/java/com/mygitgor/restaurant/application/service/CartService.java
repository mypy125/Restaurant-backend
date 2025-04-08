package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.CartItemEntity;
import com.mygitgor.restaurant.api.exceprion.cartexception.CartItemNotFoundException;
import com.mygitgor.restaurant.api.controller.DTOs.request.AddCartItemRequest;

public interface CartService {
    CartItemEntity addItemToCart(AddCartItemRequest request, String jwt)throws Exception;
    CartItemEntity updateCartItemQuantity(Long cartItemId, int quantity)throws CartItemNotFoundException;
    CartEntity removeItemFromCart(Long id, String jwt)throws Exception;
    Long calculateCartTotals(CartEntity cart)throws Exception;
    CartEntity findCartById(Long id)throws Exception;
    CartEntity findCartByUserId(Long userId)throws Exception;
    CartEntity clearCart(Long userId)throws Exception;
}

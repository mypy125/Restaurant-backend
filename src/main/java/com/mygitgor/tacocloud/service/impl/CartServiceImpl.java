package com.mygitgor.tacocloud.service.impl;

import com.mygitgor.tacocloud.domain.Cart;
import com.mygitgor.tacocloud.domain.CartItem;
import com.mygitgor.tacocloud.domain.Food;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.repository.CartItemRepository;
import com.mygitgor.tacocloud.repository.CartRepository;
import com.mygitgor.tacocloud.request.AddCartItemRequest;
import com.mygitgor.tacocloud.service.CartService;
import com.mygitgor.tacocloud.service.FoodService;
import com.mygitgor.tacocloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodService foodService;
    private final UserService userService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(request.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem : cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int quantity = cartItem.getQuantity() + request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), quantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity() * food.getPrice());
        CartItem saveCartItem = cartItemRepository.save(newCartItem);

        cart.getItem().add(saveCartItem);
        return saveCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }

        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice() * quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long id, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }

        CartItem cartItem = cartItemOptional.get();
        cart.getItem().remove(cartItem);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;

        for(CartItem cartItem : cart.getItem()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if(cartOptional.isEmpty()){
            throw new Exception("cart item not found wit id");
        }
        return cartOptional.get();
    }

    @Override
    public Cart findCartByUserId(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return cartRepository.findByCustomerId(user.getId());
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = findCartByUserId(jwt);

        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}

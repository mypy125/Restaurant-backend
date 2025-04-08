package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.CartItemEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.FoodEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.exceprion.cartexception.CartItemNotFoundException;
import com.mygitgor.restaurant.model.repository.CartItemRepository;
import com.mygitgor.restaurant.model.repository.CartRepository;
import com.mygitgor.restaurant.api.controller.DTOs.request.AddCartItemRequest;
import com.mygitgor.restaurant.application.service.CartService;
import com.mygitgor.restaurant.application.service.FoodService;
import com.mygitgor.restaurant.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CartEntity Service: Этот сервиз связаны с  корзиной покупок.
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodService foodService;
    private final UserService userService;

    /**
     * метод предназначен для добавления товара в корзину пользователя.
     * @param request пренимает foodId quantity ingredients
     * @param jwt  пренимает токен пользователя
     * @return возврошяет корзину товаров пользователя
     * @throws Exception бросает исключения Exception
     */
    @Override
    public CartItemEntity addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        UserEntity user = userService.findUserByJwtToken(jwt);
        FoodEntity food = foodService.findFoodById(request.getFoodId());
        CartEntity cart = cartRepository.findByCustomerId(user.getId());

        for(CartItemEntity cartItem : cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int quantity = cartItem.getQuantity() + request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), quantity);
            }
        }

        CartItemEntity newCartItem = new CartItemEntity();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity() * food.getPrice());
        CartItemEntity saveCartItem = cartItemRepository.save(newCartItem);

        cart.getItem().add(saveCartItem);
        return saveCartItem;
    }

    /**
     * метод предназначен для обновления количества товара в корзине пользователя
     * @param cartItemId принемает идентификатор карзины
     * @param quantity принемает количество товаров
     * @return возврошает корзину товаров
     * @throws Exception бросает исключения Exception
     */
    @Override
    public CartItemEntity updateCartItemQuantity(Long cartItemId, int quantity)throws CartItemNotFoundException{
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("CartEntity item not found"));

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice() * quantity);

        return cartItemRepository.save(cartItem);
    }

    /**
     * метод предназначен для удаления элемента из корзины пользователя.
     * @param id идентификатор карзины
     * @param jwt токен пользователя
     * @return  возврашает карзину
     * @throws Exception бросает исключения Exception
     */
    @Override
    public CartEntity removeItemFromCart(Long id, String jwt) throws Exception {
        UserEntity user = userService.findUserByJwtToken(jwt);
        CartEntity cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItemEntity> cartItemOptional = cartItemRepository.findById(id);
        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }

        CartItemEntity cartItem = cartItemOptional.get();
        cart.getItem().remove(cartItem);

        return cartRepository.save(cart);
    }

    /**
     * метод предназначен для расчета общей стоимости товаров в корзине.
     * @param cart принимает карзину
     * @return возврошает стоимость товаров
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Long calculateCartTotals(CartEntity cart) throws Exception {
        Long total = 0L;

        for(CartItemEntity cartItem : cart.getItem()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    /**
     * метод предназначен для поиска корзины по её идентификатору.
     * @param id идентификатор карзины
     * @return возврошает карзину
     * @throws Exception бросает исключения Exception
     */
    @Override
    public CartEntity findCartById(Long id) throws Exception {
        Optional<CartEntity> cartOptional = cartRepository.findById(id);

        if(cartOptional.isEmpty()){
            throw new Exception("cart item not found wit id");
        }
        return cartOptional.get();
    }

    /**
     * метод предназначен для поиска корзины по идентификатору пользователя.
     * @param userId идентификатор пользователя
     * @return возврошает карзину пользователя
     * @throws Exception бросает исключения Exception
     */
    @Override
    public CartEntity findCartByUserId(Long userId) throws Exception {
        CartEntity cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    /**
     * метод clearCart предназначен для очистки корзины пользователя.
     * @param userId идентификатор пользователя
     * @return возбрашает карзину
     * @throws Exception бросает исключения Exception
     */
    @Override
    public CartEntity clearCart(Long userId) throws Exception {
        CartEntity cart = findCartByUserId(userId);

        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}

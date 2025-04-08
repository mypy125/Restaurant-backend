package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.api.controller.DTOs.AddressDto;
import com.mygitgor.restaurant.infrastructure.database.entity.*;
import com.mygitgor.restaurant.model.repository.OrderItemRepository;
import com.mygitgor.restaurant.model.repository.OrderRepository;
import com.mygitgor.restaurant.model.repository.UserRepository;
import com.mygitgor.restaurant.api.controller.DTOs.request.OrderRequest;
import com.mygitgor.restaurant.application.service.AddressService;
import com.mygitgor.restaurant.application.service.CartService;
import com.mygitgor.restaurant.application.service.OrderService;
import com.mygitgor.restaurant.application.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * OrderEntity Service: Этот сервиз связаны с оформлением заказов.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final AddressService addressService;
    private final UserRepository userRepository;
    private final RestaurantService restaurantService;
    private final CartService cartService;


    /**
     * метод обеспечивает создание нового заказа на основе информации, полученной из запроса, и данных пользователя.
     * @param request ордерский запрос
     * @param user пользожатель для оформления заказа
     * @return возжрашает заказ
     * @throws Exception бросает исключения Exception
     */
    @Override
    public OrderEntity createOrder(OrderRequest request, UserEntity user) throws Exception {
        AddressDto shopAddress = request.getDeliveryAddress();
        AddressEntity savedAddress = addressService.saveUserAddress(shopAddress, user);

        RestaurantEntity restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

        OrderEntity createOrder = new OrderEntity();
        createOrder.setCustomer(user);
        createOrder.setCreateAt(new Date());
        createOrder.setOrderStatus("PENDING");
        createOrder.setDeliveryAddress(savedAddress);
        createOrder.setRestaurant(restaurant);

        CartEntity cart = cartService.findCartByUserId(user.getId());

        List<OrderItemEntity> orderItems = new ArrayList<>();

        for(CartItemEntity cartItem : cart.getItem()){

            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItemEntity saveOrder = orderItemRepository.save(orderItem);
            orderItems.add(saveOrder);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);

        createOrder.setItems(orderItems);
        createOrder.setTotalPrice(totalPrice);

        OrderEntity saveOrder = orderRepository.save(createOrder);
        restaurant.getOrders().add(saveOrder);

        return createOrder;

    }

    /**
     * метод обеспечивает возможность обновления статуса заказа на основе его идентификатора и переданного статуса.
     * @param orderId идентификатор заказа
     * @param orderStatus статус
     * @return возврошает заказ
     * @throws Exception бросает исключения Exception
     */
    @Override
    public OrderEntity updateOrder(Long orderId, String orderStatus) throws Exception {
        OrderEntity order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") ||
                orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")){

            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("select a valid order status");
    }

    /**
     * метод не возвращает результат, так как просто отменяет заказ, удаляя его из базы данных.
     * @param orderId идентификатор заказа
     * @throws Exception бросает исключения Exception
     */
    @Override
    public void cancelOrder(Long orderId) throws Exception {
        OrderEntity order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    /**
     *  метод предназначен для получения списка заказов пользователя по его идентификатору.
     * @param userId идентификатор пользователя
     * @return возврошает список заказов
     * @throws Exception бросает исключения Exception
     */
    @Override
    public List<OrderEntity> getUsersOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    /**
     * метод предназначен для получения списка заказов ресторана по его идентификатору и статусу заказа.
     * @param restaurantId идентификатор ресторана
     * @param orderStatus статус заказа
     * @return возврошает список зказов
     * @throws Exception бросает исключения Exception
     */
    @Override
    public List<OrderEntity> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        List<OrderEntity> orders = orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus != null){
            orders = orders.stream().filter(order ->
                    order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    /**
     * метод полезен при поиске заказа по его идентификатору для выполнения различных операций,
     * таких как обновление статуса заказа или получение информации о конкретном заказе.
     * @param orderId идентификатор заказа
     * @return возврошает заказ
     * @throws Exception бросает исключения Exception
     */
    @Override
    public OrderEntity findOrderById(Long orderId) throws Exception {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("order not found");
        }
        return optionalOrder.get();
    }
}

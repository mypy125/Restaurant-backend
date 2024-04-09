package com.mygitgor.tacocloud.service.impl;

import com.mygitgor.tacocloud.domain.Address;
import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.dto.RestaurantDto;
import com.mygitgor.tacocloud.repository.AddressRepository;
import com.mygitgor.tacocloud.repository.RestaurantRepository;
import com.mygitgor.tacocloud.request.CreateRestaurantRequest;
import com.mygitgor.tacocloud.service.RestaurantService;
import com.mygitgor.tacocloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final UserService userService;
    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
       Address address = addressRepository.save(request.getAddress());

       Restaurant restaurant = new Restaurant();
       restaurant.setAddress(address);
       restaurant.setContactInformation(restaurant.getContactInformation());
       restaurant.setCuisineType(request.getCuisineType());
       restaurant.setDescription(request.getDescription());
       restaurant.setImages(request.getImages());
       restaurant.setName(request.getName());
       restaurant.setOpeningHours(request.getOpeningHours());
       restaurant.setRegistrationTime(LocalDateTime.now());
       restaurant.setOwner(user);
       return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updateRestaurant.getCuisineType());
        }
        if(restaurant.getDescription() != null){
            restaurant.setDescription(updateRestaurant.getDescription());
        }
        if(restaurant.getName() != null){
            restaurant.setName(updateRestaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = findRestaurantByUserId(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return null;
    }

    @Override
    public List<Restaurant> searchRestaurant() {
        return null;
    }

    @Override
    public Restaurant findRestaurantById(Long id) {
        return null;
    }

    @Override
    public Restaurant findRestaurantByUserId(Long id) {
        return null;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) {
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) {
        return null;
    }
}

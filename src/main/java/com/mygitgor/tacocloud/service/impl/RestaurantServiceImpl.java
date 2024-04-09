package com.mygitgor.tacocloud.service.impl;

import com.mygitgor.tacocloud.domain.Address;
import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.dto.RestaurantDto;
import com.mygitgor.tacocloud.repository.AddressRepository;
import com.mygitgor.tacocloud.repository.RestaurantRepository;
import com.mygitgor.tacocloud.repository.UserRepository;
import com.mygitgor.tacocloud.request.CreateRestaurantRequest;
import com.mygitgor.tacocloud.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
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
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant)throws Exception {
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
    public void deleteRestaurant(Long restaurantId)throws Exception {
        Restaurant restaurant = findRestaurantByUserId(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id)throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isEmpty()){
            throw new Exception("restaurant not found wit id"+id);
        }
        return restaurant.get();
    }

    @Override
    public Restaurant findRestaurantByUserId(Long id)throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(id);
        if(restaurant == null){
            throw new Exception("restaurant not found wit owner id"+id);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user)throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setId(restaurantId);

        if(user.getFavorites().contains(restaurantDto)){
            user.getFavorites().remove(restaurantDto);
        }
        else user.getFavorites().add(restaurantDto);

        userRepository.save(user);

        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id)throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}

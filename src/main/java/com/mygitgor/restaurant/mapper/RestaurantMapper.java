package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.controller.DTOs.request.CreateRestaurantRequest;
import com.mygitgor.restaurant.domain.Restaurant;
import com.mygitgor.restaurant.dto.RestaurantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "registrationTime", ignore = true)
    @Mapping(target = "open", ignore = true)
    Restaurant fromCreateRequest(CreateRestaurantRequest request);

    @Mapping(target = "title", source = "name")
    RestaurantDto toDto(Restaurant restaurant);
}

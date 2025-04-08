package com.mygitgor.restaurant.model.domain;

import com.mygitgor.restaurant.api.controller.DTOs.RestaurantDto;
import com.mygitgor.restaurant.infrastructure.database.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModelId{
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private List<Order> orders;
    private List<RestaurantDto> favorites;
    private List<Address> addresses;
}

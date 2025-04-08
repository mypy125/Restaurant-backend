package com.mygitgor.restaurant.api.controller.DTOs.response;

import com.mygitgor.restaurant.infrastructure.database.entity.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String massage;
    private Role role;
}

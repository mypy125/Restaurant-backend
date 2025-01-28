package com.mygitgor.restaurant.controller.DTOs.response;

import com.mygitgor.restaurant.domain.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String massage;
    private Role role;
}

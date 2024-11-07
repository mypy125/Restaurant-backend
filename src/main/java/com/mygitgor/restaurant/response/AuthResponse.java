package com.mygitgor.restaurant.response;

import com.mygitgor.restaurant.domain.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String massage;
    private Role role;
}

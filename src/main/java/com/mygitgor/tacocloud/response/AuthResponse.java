package com.mygitgor.tacocloud.response;

import com.mygitgor.tacocloud.domain.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String massage;
    private Role role;
}

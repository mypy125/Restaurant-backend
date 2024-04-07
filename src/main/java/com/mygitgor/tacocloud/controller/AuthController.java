package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.config.JwtProvider;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.repository.CartRepository;
import com.mygitgor.tacocloud.repository.UserRepository;
import com.mygitgor.tacocloud.response.AuthResponse;
import com.mygitgor.tacocloud.service.CustomerUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomerUserDetailsService customerUserDetailsService;

    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user){
        return null;
    }
}

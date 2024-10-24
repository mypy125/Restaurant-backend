package com.mygitgor.tacocloud.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * класс является конфигурационным классом для Spring приложения. В нем определены несколько бенов:
 */
@Configuration
@EnableWebSecurity
public class AppConfig {

    /**
     * Этот бин создает SecurityFilterChain,
     * который определяет правила безопасности для различных HTTP-запросов.
     * Он настраивает сессии для политики без состояния,
     * авторизует запросы в зависимости от ролей пользователей,
     * добавляет фильтр для проверки токена JWT и настраивает CSRF и CORS.
     * @param http HTTP-запрос
     * @return возвращает объект SecurityFilterChain, который представляет собой цепочку фильтров безопасности Spring Security.
     * @throws Exception бросает исключения Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())  // Disable CSRF for development
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize
                        .requestMatchers("/auth/signup").permitAll()
                        .requestMatchers("/auth/signin").permitAll()
                        .requestMatchers("/api/admin/**").hasAnyRole("OWNER", "ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class);

        return http.build();

    }

    /**
     * Этот бин определяет конфигурацию CORS для разрешения запросов с определенных источников.
     * @return возвращает объект CorsConfigurationSource
     */
    private CorsConfigurationSource corsConfigurationSource() {
//        return new CorsConfigurationSource() {
//            @Override
//            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                CorsConfiguration config = new CorsConfiguration();
//                config.setAllowedOrigins(Arrays.asList(
//                        "http://localhost:8080/login", "localhost:3000"));
//                config.setAllowedMethods(Collections.singletonList("*"));
//                config.setAllowCredentials(true);
//                config.setAllowedHeaders(Collections.singletonList("*"));
//                config.setExposedHeaders(Arrays.asList("Authorization"));
//                config.setMaxAge(3000L);
//                return config;
//            }
//
//        };
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setExposedHeaders(Collections.singletonList("Authorization"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        return request -> config;
    }

    /**
     * Этот бин предоставляет PasswordEncoder, который используется для хеширования паролей пользователей,
     * чтобы обеспечить безопасность хранения паролей.
     * @return возвращает объект PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

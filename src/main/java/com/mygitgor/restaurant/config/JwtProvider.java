package com.mygitgor.restaurant.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс отвечает за создание и проверку JWT-токенов в приложении.
 * В нем содержатся методы для генерации токенов и извлечения информации из них.
 */
@Service
public class JwtProvider {

    private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    /**
     * Метод создает JWT-токен на основе предоставленной аутентификации (Authentication).
     * Он извлекает информацию об аутентифицированном пользователе, такую как его имя и роли,
     * и включает эту информацию в токен.
     * Токен подписывается с использованием секретного ключа, который хранится в классе JwtConstant.
     * @param aut принемает обект Authentication
     * @return возврошает jwt токен
     */
    public String generateToken(Authentication aut){
        Collection<? extends GrantedAuthority> authorities = aut.getAuthorities();
        String roles = populateAuthorities(authorities);
        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration((new Date(new Date().getTime()+86400000)))
                .claim("email", aut.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();
        return jwt;
    }

    /**
     * Метод формирует строку с ролями пользователя на основе коллекции разрешений (GrantedAuthority).
     * Роли разделяются запятыми и добавляются в токен в виде списка.
     * @param authorities принемает колекции (GrantedAuthority)Роли
     * @return возврошает строку authorities
     */
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority : authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }

    /**
     * Метод извлекает адрес электронной почты из JWT-токена.
     * Он проверяет подпись токена с использованием секретного ключа и извлекает данные,
     * включенные в токен, включая адрес электронной почты пользователя.
     * @param jwt принемает строку токен
     * @return возврошает адрес электронной почты пользователя
     */
    public String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }
}

package com.example.shop_hoa.core.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    // Secret key tự sinh (Trong thực tế nên đưa vào application.properties)
    private final Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final int jwtExpirationMs = 86400000; // 1 ngày

    public String generateToken(String email, Long userId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(jwtSecret)
                .compact();
    }
}
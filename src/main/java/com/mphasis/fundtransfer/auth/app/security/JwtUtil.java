package com.mphasis.fundtransfer.auth.app.security;

import com.mphasis.fundtransfer.auth.app.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    @Value("${jwt.refresh-expiration-ms}")
    private long refreshExpirationMs;

    public String generateAccessToken(User user) {
        return generateToken(user, jwtExpirationMs, "access");
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, refreshExpirationMs, "refresh");
    }

    private String generateToken(User user, long expirationMs, String tokenUse) {
        Instant now = Instant.now();
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plusMillis(expirationMs));

        Key signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("loginId", user.getLoginId())
                .claim("roles", user.getRoleNames())
                .claim("accountStatus", user.getAccountStatus().name())
                .claim("tokenUse", tokenUse)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public Instant getExpirationInstant() {
        return Instant.now().plusMillis(jwtExpirationMs);
    }
}

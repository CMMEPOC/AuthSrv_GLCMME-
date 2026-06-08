package com.mphasis.fundtransfer.auth.api.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class JwtResponse {

    private String token;
    private String tokenType = "Bearer";
    private Instant expiresAt;
    private UUID userId;
    private String loginId;
    private List<String> roles;

    public JwtResponse() {
    }

    public JwtResponse(String token, Instant expiresAt, UUID userId, String loginId, List<String> roles) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.userId = userId;
        this.loginId = loginId;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

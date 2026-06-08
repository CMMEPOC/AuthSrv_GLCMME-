package com.mphasis.fundtransfer.auth.api.dto.response;

import java.time.Instant;

public class JwtResponse {

    private String token;
    private String tokenType = "Bearer";
    private Instant expiresAt;

    public JwtResponse() {
    }

    public JwtResponse(String token, Instant expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
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
}

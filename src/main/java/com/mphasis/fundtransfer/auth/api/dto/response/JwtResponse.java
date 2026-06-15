package com.mphasis.fundtransfer.auth.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class JwtResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private Instant expiresAt;
    private UserInfo user;

    public JwtResponse() {
    }

    public JwtResponse(String accessToken,
                       String refreshToken,
                       Instant expiresAt,
                       UserInfo user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    @Setter
    @Getter
    public static class UserInfo {
        private UUID id;
        private String username;
        private List<String> roles;
        private String accountStatus;

        public UserInfo() {
        }

        public UserInfo(UUID id, String username, List<String> roles, String accountStatus) {
            this.id = id;
            this.username = username;
            this.roles = roles;
            this.accountStatus = accountStatus;
        }
    }

}

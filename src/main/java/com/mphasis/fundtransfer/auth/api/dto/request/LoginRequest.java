package com.mphasis.fundtransfer.auth.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Login ID must not be blank")
    private String loginId;

    @NotBlank(message = "Password must not be blank")
    private String password;

    public LoginRequest() {
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

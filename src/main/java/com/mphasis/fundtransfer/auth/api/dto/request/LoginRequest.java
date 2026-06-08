package com.mphasis.fundtransfer.auth.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    @NotBlank(message = "Login ID must not be blank")
    private String loginId;

    @NotBlank(message = "Password must not be blank")
    private String password;

    public LoginRequest() {
    }

}

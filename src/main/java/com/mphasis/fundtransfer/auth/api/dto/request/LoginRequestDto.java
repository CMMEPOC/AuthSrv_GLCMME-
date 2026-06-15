package com.mphasis.fundtransfer.auth.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "Login ID must not be blank")
    private String loginId;

    @NotBlank(message = "Password must not be blank")
    private String password;
}

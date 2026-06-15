package com.mphasis.fundtransfer.auth.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RBACRequestDto {

    @NotBlank(message = "Endpoint must not be blank")
    private String endpointName;

}

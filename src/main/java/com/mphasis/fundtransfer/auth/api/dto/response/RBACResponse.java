package com.mphasis.fundtransfer.auth.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RBACResponse {

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("redirectUrl")
    private String redirectUrl;

    public RBACResponse(String s){
        this.msg = s;
        this.redirectUrl = "/api/v1/home";
    }
}

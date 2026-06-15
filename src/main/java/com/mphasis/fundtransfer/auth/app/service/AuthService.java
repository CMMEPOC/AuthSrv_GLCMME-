package com.mphasis.fundtransfer.auth.app.service;

import com.mphasis.fundtransfer.auth.api.dto.request.LoginRequestDto;
import com.mphasis.fundtransfer.auth.api.dto.request.RBACRequestDto;
import com.mphasis.fundtransfer.auth.api.dto.response.JwtResponse;
import com.mphasis.fundtransfer.auth.api.dto.response.RBACResponse;

public interface AuthService {
    JwtResponse login(LoginRequestDto request);
    RBACResponse checkRBAC(String userId, RBACRequestDto request);
}

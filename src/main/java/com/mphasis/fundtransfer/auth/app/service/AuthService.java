package com.mphasis.fundtransfer.auth.app.service;

import com.mphasis.fundtransfer.auth.api.dto.request.LoginRequest;
import com.mphasis.fundtransfer.auth.api.dto.response.JwtResponse;

public interface AuthService {
    JwtResponse login(LoginRequest request);
}

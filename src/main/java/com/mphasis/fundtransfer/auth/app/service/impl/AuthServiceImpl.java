package com.mphasis.fundtransfer.auth.app.service.impl;

import com.mphasis.fundtransfer.auth.api.AuthAccountStatus;
import com.mphasis.fundtransfer.auth.api.dto.request.LoginRequest;
import com.mphasis.fundtransfer.auth.api.dto.response.JwtResponse;
import com.mphasis.fundtransfer.auth.app.entity.User;
import com.mphasis.fundtransfer.auth.app.repository.UserRepository;
import com.mphasis.fundtransfer.auth.app.security.JwtUtil;
import com.mphasis.fundtransfer.auth.app.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (user.getAccountStatus() == AuthAccountStatus.Inactive) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account is inactive");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String role = "ROLE_" + user.getRole().name();
        JwtResponse.UserInfo userInfo = new JwtResponse.UserInfo(
                user.getId(),
                user.getLoginId(),
                List.of(role),
                user.getAccountStatus().name()
        );

        return new JwtResponse(
                jwtUtil.generateAccessToken(user),
                jwtUtil.generateRefreshToken(user),
                jwtUtil.getExpirationInstant(),
                userInfo
        );
    }
}

package com.mphasis.fundtransfer.authentication.controller;

import com.mphasis.fundtransfer.authentication.dto.AuthAccountStatus;
import com.mphasis.fundtransfer.authentication.dto.JwtResponse;
import com.mphasis.fundtransfer.authentication.dto.LoginRequest;
import com.mphasis.fundtransfer.authentication.entity.User;
import com.mphasis.fundtransfer.authentication.repository.UserRepository;
import jakarta.validation.Valid;
import com.mphasis.fundtransfer.authentication.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = userRepository.findByLoginId(request.getLoginId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (user.getAccountStatus() == AuthAccountStatus.Inactive) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account is inactive");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);
        JwtResponse response = new JwtResponse(token, jwtUtil.getExpirationInstant());
        return ResponseEntity.ok(response);
    }
}

package com.mphasis.fundtransfer.auth.app.controller;

import com.mphasis.fundtransfer.auth.api.dto.request.LoginRequestDto;
import com.mphasis.fundtransfer.auth.api.dto.request.RBACRequestDto;
import com.mphasis.fundtransfer.auth.api.dto.response.JwtResponse;
import com.mphasis.fundtransfer.auth.api.dto.response.RBACResponse;
import com.mphasis.fundtransfer.auth.api.interfaces.AuthInterface;
import com.mphasis.fundtransfer.auth.app.service.AuthService;
import com.mphasis.fundtransfer.auth.app.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController implements AuthInterface {

    private AuthService authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/user/{user_id}")
    public ResponseEntity<RBACResponse> checkRbac( @PathVariable(name="user_id", required = true) String userName,
            @Valid @RequestBody RBACRequestDto rbacRequestDto){
        RBACResponse rbacResponse = authService.checkRBAC(userName, rbacRequestDto);
        if(rbacResponse == null){
            return new ResponseEntity<>(new RBACResponse("You do not have the required permissions", null), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(rbacResponse, HttpStatus.ACCEPTED);
    }
}

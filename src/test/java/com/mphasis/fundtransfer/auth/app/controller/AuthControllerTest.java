package com.mphasis.fundtransfer.auth.app.controller;

import com.mphasis.fundtransfer.auth.api.dto.request.LoginRequest;
import com.mphasis.fundtransfer.auth.api.dto.response.JwtResponse;
import com.mphasis.fundtransfer.auth.app.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Test
    void loginReturnsJwtResponseForValidCredentials() throws Exception {
        UUID userId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        JwtResponse.UserInfo userInfo = new JwtResponse.UserInfo(
                userId,
                "admin_xyz",
                List.of("ROLE_ADMIN"),
                "ACTIVE"
        );
        JwtResponse response = new JwtResponse(
                "access-token",
                "refresh-token",
                Instant.parse("2026-06-10T10:30:00Z"),
                userInfo
        );

        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "loginId": "admin_xyz",
                                  "password": "Password@123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("access-token"))
                .andExpect(jsonPath("$.refreshToken").value("refresh-token"))
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.expiresAt").value("2026-06-10T10:30:00Z"))
                .andExpect(jsonPath("$.user.id").value(userId.toString()))
                .andExpect(jsonPath("$.user.username").value("admin_xyz"))
                .andExpect(jsonPath("$.user.roles[0]").value("ROLE_ADMIN"))
                .andExpect(jsonPath("$.user.accountStatus").value("ACTIVE"));

        verify(authService).login(any(LoginRequest.class));
    }

    @Test
    void loginReturnsUnauthorizedForInvalidCredentials() throws Exception {
        when(authService.login(any(LoginRequest.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "loginId": "admin_xyz",
                                  "password": "wrong-password"
                                }
                                """))
                .andExpect(status().isUnauthorized());

        verify(authService).login(any(LoginRequest.class));
    }

    @Test
    void loginReturnsBadRequestWhenRequiredFieldsAreBlank() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "loginId": "",
                                  "password": ""
                                }
                                """))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(authService);
    }
}

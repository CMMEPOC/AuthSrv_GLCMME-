package com.mphasis.fundtransfer.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mphasis.fundtransfer.auth.BaseIntegrationTest;
import com.mphasis.fundtransfer.auth.api.dto.request.LoginRequestDto;
import com.mphasis.fundtransfer.auth.api.dto.request.RBACRequestDto;
import com.mphasis.fundtransfer.auth.api.dto.response.RBACResponse;
import com.mphasis.fundtransfer.auth.app.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Address Controller")
public class AuthControllerTest extends BaseIntegrationTest {

    @MockitoBean
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        Mockito.reset(authService);  // clean slate for every test
    }

    @Test
    void loginReturnsUnauthorizedForInvalidCredentials() throws Exception {
        LoginRequestDto loginRequestDto =  new LoginRequestDto("admin_xyz", "wrong-password");
        when(authService.login(any(LoginRequestDto.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andExpect(status().isUnauthorized());

        verify(authService).login(any(LoginRequestDto.class));
    }

    @Test
    void checkRbac_WhenAuthorized_ReturnsAccepted() throws Exception {
        String userId = "testUser";
        RBACRequestDto dto = new RBACRequestDto(); // Populate with test data
        dto.setEndpointName("/api/v1/random");
        RBACResponse mockResponse = new RBACResponse("You are Authorized", "/v1/api/success");

        when(authService.checkRBAC(any(String.class), any(RBACRequestDto.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/auth/rbac/user/{user_id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.msg").value("You are Authorized"));
    }

    @Test
    void checkRbac_WhenUnauthorized_ReturnsUnauthorized() throws Exception {
        String userId = "testUser";
        RBACRequestDto dto = new RBACRequestDto();
        dto.setEndpointName("/api/v1/random");
        when(authService.checkRBAC(eq(userId), any(RBACRequestDto.class))).thenReturn(null);

        mockMvc.perform(post("/api/v1/auth/rbac/user/{user_id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.msg").value("You do not have the required permissions"));
    }

}

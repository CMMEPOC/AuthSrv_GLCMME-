package com.mphasis.fundtransfer.auth.app.service.impl;

import com.mphasis.fundtransfer.auth.api.constants.AuthAccountStatus;
import com.mphasis.fundtransfer.auth.api.constants.AuthRole;
import com.mphasis.fundtransfer.auth.api.dto.request.LoginRequestDto;
import com.mphasis.fundtransfer.auth.api.dto.request.RBACRequestDto;
import com.mphasis.fundtransfer.auth.api.dto.response.JwtResponse;
import com.mphasis.fundtransfer.auth.api.dto.response.RBACResponse;
import com.mphasis.fundtransfer.auth.app.entity.*;
import com.mphasis.fundtransfer.auth.app.repository.*;
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
    private final UserRoleRepository userRoleRepository;
    private final RoleEndpointRepository roleEndpointRepository;
    private final EndpointRepository endpointRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           RoleRepository roleRepository,
                           RoleEndpointRepository roleEndpointRepository,
                           EndpointRepository endpointRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleEndpointRepository =  roleEndpointRepository;
        this.endpointRepository = endpointRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtResponse login(LoginRequestDto request) {
        UserEntity user = userRepository.findByUsername(request.getLoginId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
        if (user.getAccountStatus() == AuthAccountStatus.INACTIVE) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account is inactive");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        String role = "ROLE_" + AuthRole.CUSTOMER;
        JwtResponse.UserInfo userInfo = new JwtResponse.UserInfo(
                user.getUserId(),
                user.getUsername(),
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

    @Override
    public RBACResponse checkRBAC(String userName, RBACRequestDto request) {
        RBACResponse rbacResponse = null;
        // User details validation
        try {
            UserEntity userEntity = userRepository.findByUsername(userName.trim()).orElse(null);
            if(userEntity==null){
                return null;
            }
            UserRoleEntity userRoleEntity = userRoleRepository.findByUserRoleEmbedding_UserId(userEntity.getUserId());
            // Assuming each user in the system has atmost 1 role
            Integer roleId = userRoleEntity.getUserRoleEmbedding().getRoleId();
            EndpointEntity endpointEntity = endpointRepository.findByEndpointName(request.getEndpointName());
            Integer endpointId = endpointEntity.getId();
            RoleEndpointEmbedding roleEndpointEmbedding = new RoleEndpointEmbedding(roleId, endpointId);
            RoleEndpointEntity roleEndpointEntity = roleEndpointRepository.findById(roleEndpointEmbedding).orElse(null);
            if(roleEndpointEntity==null){
                return null;
            }
            rbacResponse = new RBACResponse("You are authorized");
        }
        catch(Exception e ){
            System.out.println("Exception : "+ e.getMessage());
        }
        return rbacResponse;
    }
}

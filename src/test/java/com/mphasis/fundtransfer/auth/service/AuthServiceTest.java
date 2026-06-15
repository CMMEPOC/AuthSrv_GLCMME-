package com.mphasis.fundtransfer.auth.service;

import com.mphasis.fundtransfer.auth.app.repository.UserRepository;
import com.mphasis.fundtransfer.auth.app.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

//@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    // TODO : Write tests here

}

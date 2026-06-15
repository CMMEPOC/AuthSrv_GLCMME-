package com.mphasis.fundtransfer.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mphasis.fundtransfer.auth.app.service.impl.AuthServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestApplicationConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())         // for LocalDate, LocalDateTime etc.
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }



    @Bean
    @Primary
    public AuthServiceImpl authService() {
        return Mockito.mock(AuthServiceImpl.class);
    }

}

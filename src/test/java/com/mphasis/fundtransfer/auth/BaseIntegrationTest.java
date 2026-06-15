package com.mphasis.fundtransfer.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mphasis.fundtransfer.auth.config.TestAasApplication;
import com.mphasis.fundtransfer.auth.config.TestApplicationConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@SpringBootTest(classes = TestAasApplication.class)
@ActiveProfiles("test")
@Import(TestApplicationConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public abstract class BaseIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private Environment environment;

    @BeforeAll
    void checkProfile() {
        System.out.println("Active profiles: " +
                Arrays.toString(environment.getActiveProfiles()));
    }
}

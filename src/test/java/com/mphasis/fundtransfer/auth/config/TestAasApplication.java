package com.mphasis.fundtransfer.auth.config;


import com.mphasis.fundtransfer.auth.app.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(
scanBasePackages = {
        "com.mphasis.fundtransfer.auth"   // ← scans the full root package
})
@ComponentScan(
        basePackages = "com.mphasis.fundtransfer.auth",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = SecurityConfig.class           // ← exclude app's security config
        )
)
public class TestAasApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestAasApplication.class, args);
    }
}

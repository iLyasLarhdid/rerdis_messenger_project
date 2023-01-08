package com.larhdid.messenger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    @Bean
    public BCryptPasswordEncoder mypassword(){
        return new BCryptPasswordEncoder(10);
    }
}

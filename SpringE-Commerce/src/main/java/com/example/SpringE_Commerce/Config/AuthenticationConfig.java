package com.example.SpringE_Commerce.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.SpringE_Commerce.Security.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {
    
    private final UserDetailsServiceImpl userDetailsService;
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider AuthProvider = new DaoAuthenticationProvider();
        AuthProvider.setUserDetailsService(userDetailsService);
        AuthProvider.setPasswordEncoder(GetPasswordEncoder());
        return AuthProvider;
    }

    @Bean
    public PasswordEncoder GetPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

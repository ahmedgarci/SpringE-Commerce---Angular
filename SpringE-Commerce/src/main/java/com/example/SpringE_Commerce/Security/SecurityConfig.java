package com.example.SpringE_Commerce.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.SpringE_Commerce.Config.AuthenticationConfig;
import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final AuthenticationConfig authenticationConfig;
    private final JwtCustomFilter jwtCustomFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        .cors(withDefaults())
        .csrf(csrf->csrf.disable()).
        authorizeHttpRequests(request->request.requestMatchers(
        //TO DO    
        "/","/"
            )
        .permitAll().anyRequest().authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationConfig.authenticationProvider())
        .addFilterBefore(jwtCustomFilter,UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
            
        
    }
}

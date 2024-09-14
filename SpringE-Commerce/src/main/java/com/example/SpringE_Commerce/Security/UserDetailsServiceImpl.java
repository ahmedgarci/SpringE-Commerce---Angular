package com.example.SpringE_Commerce.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringE_Commerce.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String UserEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(UserEmail)
            .orElseThrow(()->new UsernameNotFoundException("user not found"))
        ;
    }
    
}

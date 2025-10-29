package com.example.E_Certificate_Generation_and_Verification_System.service;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.E_Certificate_Generation_and_Verification_System.entity.User;
import com.example.E_Certificate_Generation_and_Verification_System.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + (user.getRole() == null ? "USER" : user.getRole()));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword() == null ? "" : user.getPassword())
                .authorities(Collections.singleton(authority))
                .build();
    }
}

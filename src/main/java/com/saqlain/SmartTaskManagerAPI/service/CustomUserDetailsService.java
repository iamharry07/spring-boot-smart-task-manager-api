package com.saqlain.SmartTaskManagerAPI.service;

import com.saqlain.SmartTaskManagerAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.saqlain.SmartTaskManagerAPI.entity.Users;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String email) {

        Users user = userRepository.findByEmail(email).orElseThrow();

        return User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole().getName())
                .build();

    }
}

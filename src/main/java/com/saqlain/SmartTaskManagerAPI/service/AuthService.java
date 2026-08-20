package com.saqlain.SmartTaskManagerAPI.service;

import com.saqlain.SmartTaskManagerAPI.dto.request.LoginRequest;
import com.saqlain.SmartTaskManagerAPI.dto.request.RegisterRequest;
import com.saqlain.SmartTaskManagerAPI.entity.Role;
import com.saqlain.SmartTaskManagerAPI.entity.User;
import com.saqlain.SmartTaskManagerAPI.exception.EmailAlreadyExistsException;
import com.saqlain.SmartTaskManagerAPI.exception.InvalidCredentialsException;
import com.saqlain.SmartTaskManagerAPI.repository.RoleRepository;
import com.saqlain.SmartTaskManagerAPI.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("User with this Email already Exists");
        }

        Role role = roleRepository.findByName("USER").orElseThrow();
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
        user.setRole(role);
        user.setCreatedAt(Timestamp.from(Instant.now()));

        userRepository.save(user);

    }

    public void login(LoginRequest request) {

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        User user = userOptional.orElseThrow(() -> new InvalidCredentialsException("Invalid Email or password"));
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new InvalidCredentialsException("Invalid Email or Password");
        }
    }

}

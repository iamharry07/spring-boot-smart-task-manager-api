package com.saqlain.SmartTaskManagerAPI.controller;

import com.saqlain.SmartTaskManagerAPI.dto.request.LoginRequest;
import com.saqlain.SmartTaskManagerAPI.dto.request.RefreshTokenRequest;
import com.saqlain.SmartTaskManagerAPI.dto.request.RegisterRequest;
import com.saqlain.SmartTaskManagerAPI.dto.response.LoginResponse;
import com.saqlain.SmartTaskManagerAPI.dto.response.RefreshTokenResponse;
import com.saqlain.SmartTaskManagerAPI.entity.Users;
import com.saqlain.SmartTaskManagerAPI.exception.UserNotFoundException;
import com.saqlain.SmartTaskManagerAPI.repository.UserRepository;
import com.saqlain.SmartTaskManagerAPI.service.AuthService;
import com.saqlain.SmartTaskManagerAPI.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService tokenService;
    private final UserRepository userRepository;


    @PostMapping("/register")
    public void registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return tokenService.refreshAccessToken(request);
    }

    @PostMapping("/logout")
    public void deleteRefreshToken(Users user,@RequestHeader("Authorization") String authHeader) {

        Optional<Users> users = userRepository.findByEmail(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName());
        if (users.isPresent()) {
            user = users.get();
            String token = authHeader.substring(7);
            tokenService.deleteRefreshToken(user,token);
        }else {
            throw new UserNotFoundException("User Not Found");
        }
    }


}

package com.saqlain.SmartTaskManagerAPI.controller;

import com.saqlain.SmartTaskManagerAPI.dto.request.LoginRequest;
import com.saqlain.SmartTaskManagerAPI.dto.request.RefreshTokenRequest;
import com.saqlain.SmartTaskManagerAPI.dto.request.RegisterRequest;
import com.saqlain.SmartTaskManagerAPI.dto.response.LoginResponse;
import com.saqlain.SmartTaskManagerAPI.dto.response.RefreshTokenResponse;
import com.saqlain.SmartTaskManagerAPI.service.AuthService;
import com.saqlain.SmartTaskManagerAPI.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService tokenService;


    @PostMapping("/register")
    public void registerUser(@RequestBody @Valid RegisterRequest registerRequest){
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request){
         return authService.login(request);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@RequestBody @Valid RefreshTokenRequest request){
        return tokenService.refreshAccessToken(request);
    }



}

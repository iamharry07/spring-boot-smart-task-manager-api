package com.saqlain.SmartTaskManagerAPI.controller;

import com.saqlain.SmartTaskManagerAPI.dto.request.LoginRequest;
import com.saqlain.SmartTaskManagerAPI.dto.request.RegisterRequest;
import com.saqlain.SmartTaskManagerAPI.dto.response.LoginResponse;
import com.saqlain.SmartTaskManagerAPI.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public void registerUser(@RequestBody @Valid RegisterRequest registerRequest){
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginRequest request){
         authService.login(request);
    }



}

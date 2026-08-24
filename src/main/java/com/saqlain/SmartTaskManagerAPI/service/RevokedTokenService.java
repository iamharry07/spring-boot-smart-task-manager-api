package com.saqlain.SmartTaskManagerAPI.service;

import com.saqlain.SmartTaskManagerAPI.entity.RevokedToken;
import com.saqlain.SmartTaskManagerAPI.repository.RevokedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RevokedTokenService {
    private final RevokedTokenRepository tokenRepository;

    public void revokeToken(String token) {
        RevokedToken revokedToken = new RevokedToken();
        revokedToken.setToken(token);
        tokenRepository.save(revokedToken);
    }

    public boolean isTokenRevoked(String token) {
        return tokenRepository.existsByToken(token);

    }
}

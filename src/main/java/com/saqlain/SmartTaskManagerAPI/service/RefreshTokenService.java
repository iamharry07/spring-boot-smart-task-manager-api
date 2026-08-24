package com.saqlain.SmartTaskManagerAPI.service;

import com.saqlain.SmartTaskManagerAPI.dto.request.RefreshTokenRequest;
import com.saqlain.SmartTaskManagerAPI.dto.response.RefreshTokenResponse;
import com.saqlain.SmartTaskManagerAPI.entity.RefreshToken;
import com.saqlain.SmartTaskManagerAPI.entity.RevokedToken;
import com.saqlain.SmartTaskManagerAPI.entity.Users;
import com.saqlain.SmartTaskManagerAPI.exception.ExpiredTokenException;
import com.saqlain.SmartTaskManagerAPI.exception.TokenNotFoundException;
import com.saqlain.SmartTaskManagerAPI.repository.RefreshTokenRepository;
import com.saqlain.SmartTaskManagerAPI.repository.RevokedTokenRepository;
import io.jsonwebtoken.io.Encoders;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class RefreshTokenService {


    private final RefreshTokenRepository tokenRepository;
    private final RevokedTokenRepository revokedTokenRepository;
    private final JwtService jwtService;
    private RefreshToken refreshToken;

    public RefreshToken createRefreshToken(Users user) {
        Optional<RefreshToken> token = tokenRepository.findByUserId(user.getId());

        byte[] key = new byte[32];
        SecureRandom secretRandom = new SecureRandom();

        secretRandom.nextBytes(key);
        String secretKey = Encoders.BASE64.encode(key);

        if (token.isPresent()) {
            refreshToken = token.get();
            if (new Timestamp(System.currentTimeMillis()).before(refreshToken.getExpiryDate())) {
                return refreshToken;
            } else {
                refreshToken.setToken(secretKey);
                refreshToken.setExpiryDate(new Timestamp(System.currentTimeMillis() + 30 * 60 * 60 * 1000));
                refreshToken.setUser(user);

                tokenRepository.save(refreshToken);
                return refreshToken;
            }

        } else {
            refreshToken = new RefreshToken();
            refreshToken.setToken(secretKey);
            refreshToken.setExpiryDate(new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000));
            refreshToken.setUser(user);

            tokenRepository.save(refreshToken);
            return refreshToken;
        }
    }

    public RefreshToken verifyRefreshToken(String token) {
        Optional<RefreshToken> refreshTokenValue = tokenRepository.findByToken(token);
        if (refreshTokenValue.isPresent()) {
            refreshToken = refreshTokenValue.get();
            if (new Timestamp(System.currentTimeMillis()).before(refreshToken.getExpiryDate())) {
                return refreshToken;
            } else {
                throw new ExpiredTokenException("Token Expired");
            }
        } else {
            throw new TokenNotFoundException("Token Not Found");
        }
    }

    public RefreshTokenResponse refreshAccessToken(RefreshTokenRequest request) {

        String accessToken = jwtService.generateToken(verifyRefreshToken(request.getRefreshToken()).getUser().getEmail());
        return new RefreshTokenResponse(accessToken);
    }

    @Transactional
    public void deleteRefreshToken(Users user, String token) {

        Optional<RefreshToken> refreshTokenOptional = tokenRepository.findByUserId(user.getId());
        if (refreshTokenOptional.isPresent()) {
            Long id = refreshTokenOptional.get().getUser().getId();
            RevokedToken revokedToken = new RevokedToken();
            revokedToken.setToken(token);
            revokedToken.setExpiryDate(new Timestamp(jwtService.extractExpiration(token).getTime()));
            revokedTokenRepository.save(revokedToken);
            tokenRepository.deleteByUserId(id);
        } else {
            throw new TokenNotFoundException("Token Not Found");
        }
    }
}

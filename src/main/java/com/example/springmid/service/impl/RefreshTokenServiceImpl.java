package com.example.springmid.service.impl;

import com.example.springmid.entity.RefreshToken;
import com.example.springmid.repository.CustomerRepository;
import com.example.springmid.repository.RefreshTokenRepository;
import com.example.springmid.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomerRepository customerRepository;

    public RefreshToken createRefreshToken(String username) {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByCustomerUsername(username);

        if (refreshTokenOptional.isPresent()) {
            return refreshTokenOptional.get();
        }

        RefreshToken refreshToken = RefreshToken.builder()
                .customer(customerRepository.findByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(36000000)) // 10 hours
                .build();

        return refreshTokenRepository.save(refreshToken);
    }


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

}
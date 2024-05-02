package com.example.springmid.service;

import com.example.springmid.dto.request.AuthRequestDTO;
import com.example.springmid.dto.request.AuthResponseDTO;
import com.example.springmid.dto.request.RefreshTokenRequestDTO;

public interface AuthService {
    AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);

    AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}

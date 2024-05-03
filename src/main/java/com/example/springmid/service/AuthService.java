package com.example.springmid.service;

import com.example.springmid.dto.request.AuthRequestDTO;
import com.example.springmid.dto.request.AuthResponseDTO;
import com.example.springmid.dto.request.CustomerRequestDTO;
import com.example.springmid.dto.request.RefreshTokenRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);

    AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    ResponseEntity<?> signUp(CustomerRequestDTO requestDTO, HttpServletRequest servletRequest) throws MessagingException;

    String verifyEmail(String token);
}

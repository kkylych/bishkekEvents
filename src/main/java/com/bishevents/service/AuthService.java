package com.bishevents.service;

import com.bishevents.dto.request.AuthRequestDTO;
import com.bishevents.dto.response.AuthResponseDTO;
import com.bishevents.dto.request.UserRequestDTO;
import com.bishevents.dto.request.RefreshTokenRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);

    AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    ResponseEntity<?> signUp(UserRequestDTO requestDTO, HttpServletRequest servletRequest) throws MessagingException;

    String verifyEmail(String token);
}

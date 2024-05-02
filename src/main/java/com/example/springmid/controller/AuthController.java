package com.example.springmid.controller;

import com.example.springmid.dto.request.AuthRequestDTO;
import com.example.springmid.dto.request.AuthResponseDTO;
import com.example.springmid.dto.request.RefreshTokenRequestDTO;
import com.example.springmid.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDTO authenticate(@RequestBody AuthRequestDTO authRequestDTO){
        return authService.authenticate(authRequestDTO);
    }

    @PostMapping("/refreshToken")
    public AuthResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return authService.refreshToken(refreshTokenRequestDTO);
    }

}

package com.example.springmid.service.impl;

import com.example.springmid.dto.request.AuthRequestDTO;
import com.example.springmid.dto.request.AuthResponseDTO;
import com.example.springmid.dto.request.CustomerRequestDTO;
import com.example.springmid.dto.request.RefreshTokenRequestDTO;
import com.example.springmid.entity.Customer;
import com.example.springmid.entity.RefreshToken;
import com.example.springmid.enums.Role;
import com.example.springmid.exception.GeneralException;
import com.example.springmid.mapper.CustomerMapper;
import com.example.springmid.repository.CustomerRepository;
import com.example.springmid.service.AuthService;
import com.example.springmid.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return AuthResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                    .refreshToken(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @Override
    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getCustomer)
                .map(customer -> {
                    String accessToken = jwtService.GenerateToken(customer.getUsername());
                    return AuthResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

    @Override
    public ResponseEntity<?> signUp(CustomerRequestDTO requestDTO) {
        if (customerRepository.existsByUsername(requestDTO.getUsername())) {
            throw new GeneralException("Username already exists");
        }

        if (customerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new GeneralException("Email already exists");
        }
        Customer customer = customerMapper.toEntity(requestDTO);
        customer.setRole(Role.CUSTOMER);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);

        return ResponseEntity.ok("Successfully signed up! Now you can authenticate.");
    }
}

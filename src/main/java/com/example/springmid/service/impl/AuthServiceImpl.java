package com.example.springmid.service.impl;

import com.example.springmid.dto.request.AuthRequestDTO;
import com.example.springmid.dto.request.AuthResponseDTO;
import com.example.springmid.dto.request.CustomerRequestDTO;
import com.example.springmid.dto.request.RefreshTokenRequestDTO;
import com.example.springmid.entity.ConfirmationToken;
import com.example.springmid.entity.Customer;
import com.example.springmid.entity.RefreshToken;
import com.example.springmid.enums.Role;
import com.example.springmid.enums.Status;
import com.example.springmid.exception.GeneralException;
import com.example.springmid.mapper.CustomerMapper;
import com.example.springmid.repository.ConfirmationTokenRepository;
import com.example.springmid.repository.CustomerRepository;
import com.example.springmid.service.AuthService;
import com.example.springmid.service.MailSenderService;
import com.example.springmid.service.RefreshTokenService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final MailSenderService emailService;

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
    public ResponseEntity<?> signUp(CustomerRequestDTO requestDTO, HttpServletRequest servletRequest) throws MessagingException {
        if (customerRepository.existsByUsername(requestDTO.getUsername())) {
            throw new GeneralException("Username already exists");
        }

        if (customerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new GeneralException("Email already exists");
        }
        Customer customer = customerMapper.toEntity(requestDTO);
        customer.setRole(Role.CUSTOMER);
        customer.setStatus(Status.NOT_VERIFIED);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);

        sendConfirmationToken(customer, servletRequest);
        return new ResponseEntity<>("Successfully signed up! Email verification link was sent to your email.", HttpStatus.CREATED);
    }

    @Override
    public String verifyEmail(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new GeneralException("Such token does not exist"));

        if (isTokenExpired(confirmationToken)) {
            throw new GeneralException("Token is expired");
        }
        Customer customer = confirmationToken.getCustomer();
        customer.setStatus(Status.VERIFIED);
        customerRepository.save(customer);

        confirmationTokenRepository.delete(confirmationToken);

        return "You successfully verified your email!";
    }

    public void sendConfirmationToken(Customer customer, HttpServletRequest request) throws MessagingException {
        ConfirmationToken token = createConfirmationToken(customer);
        String confirmationUrl = getConfirmationUrl(request, token.getToken());

        emailService.sendConfirmationEmail(token, confirmationUrl);
    }

    private ConfirmationToken createConfirmationToken(Customer customer) {
        String randomString = UUID.randomUUID().toString();

        Optional<ConfirmationToken> token = confirmationTokenRepository.findByCustomer(customer);
        if (token.isPresent()) {
            token.get().setToken(randomString);
            token.get().setDates(LocalDateTime.now());
            return confirmationTokenRepository.save(token.get());
        } else {
            ConfirmationToken newToken = new ConfirmationToken(randomString, customer);
            return confirmationTokenRepository.save(newToken);
        }
    }

    private String getConfirmationUrl(HttpServletRequest servletRequest, String token) {
        return "http://" + servletRequest.getServerName() + ":"
                + servletRequest.getServerPort() + "/api/v1/verify?token=" + token;
    }

    private boolean isTokenExpired(ConfirmationToken token) {
        LocalDateTime currentDate = LocalDateTime.now();
        return currentDate.isAfter(token.getExpiredAt());
    }
}

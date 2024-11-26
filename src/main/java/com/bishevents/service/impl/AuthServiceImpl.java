package com.bishevents.service.impl;

import com.bishevents.dto.request.AuthRequestDTO;
import com.bishevents.dto.response.AuthResponseDTO;
import com.bishevents.dto.request.UserRequestDTO;
import com.bishevents.dto.request.RefreshTokenRequestDTO;
import com.bishevents.entity.ConfirmationToken;
import com.bishevents.entity.User;
import com.bishevents.entity.RefreshToken;
import com.bishevents.enums.Role;
import com.bishevents.enums.Status;
import com.bishevents.exception.GeneralException;
import com.bishevents.mapper.UserMapper;
import com.bishevents.repository.ConfirmationTokenRepository;
import com.bishevents.repository.UserRepository;
import com.bishevents.service.AuthService;
import com.bishevents.service.MailSenderService;
import com.bishevents.service.RefreshTokenService;
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
    private final UserRepository userRepository;
    private final UserMapper userMapper;
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
                .map(RefreshToken::getUser)
                .map(customer -> {
                    String accessToken = jwtService.GenerateToken(customer.getUsername());
                    return AuthResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

    @Override
    public ResponseEntity<?> signUp(UserRequestDTO requestDTO, HttpServletRequest servletRequest) throws MessagingException {
        if (userRepository.existsByUsername(requestDTO.getUsername())) {
            throw new GeneralException("Username already exists");
        }

        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new GeneralException("Email already exists");
        }
        User user = userMapper.toEntity(requestDTO);
        user.setRole(Role.USER);
        user.setStatus(Status.NOT_VERIFIED);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        sendConfirmationToken(user, servletRequest);
        return new ResponseEntity<>("Successfully signed up! Email verification link was sent to your email.", HttpStatus.CREATED);
    }

    @Override
    public String verifyEmail(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new GeneralException("Such token does not exist"));

        if (isTokenExpired(confirmationToken)) {
            throw new GeneralException("Token is expired");
        }
        User user = confirmationToken.getUser();
        user.setStatus(Status.VERIFIED);
        userRepository.save(user);

        confirmationTokenRepository.delete(confirmationToken);

        return "You successfully verified your email!";
    }

    public void sendConfirmationToken(User user, HttpServletRequest request) throws MessagingException {
        ConfirmationToken token = createConfirmationToken(user);
        String confirmationUrl = getConfirmationUrl(request, token.getToken());

        emailService.sendConfirmationEmail(token, confirmationUrl);
    }

    private ConfirmationToken createConfirmationToken(User user) {
        String randomString = UUID.randomUUID().toString();

        Optional<ConfirmationToken> token = confirmationTokenRepository.findByUser(user);
        if (token.isPresent()) {
            token.get().setToken(randomString);
            token.get().setDates(LocalDateTime.now());
            return confirmationTokenRepository.save(token.get());
        } else {
            ConfirmationToken newToken = new ConfirmationToken(randomString, user);
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

package com.example.springmid.service.impl;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.request.UserRequestDTO;
import com.example.springmid.entity.User;
import com.example.springmid.enums.Role;
import com.example.springmid.exception.GeneralException;
import com.example.springmid.mapper.UserMapper;
import com.example.springmid.repository.UserRepository;
import com.example.springmid.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerResponseDTO create(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new GeneralException("Username already exists");
        }

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new GeneralException("Email already exists");
        }
        User user = userMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Override
    public CustomerResponseDTO update(User user, String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new GeneralException("User with such username exists");
        }

        if (userRepository.existsByEmail(email)) {
            throw new GeneralException("User with such email exists");
        }

        user.setUsername(username);
        user.setEmail(email);
        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Override
    public CustomerResponseDTO get(Long id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow(() -> new GeneralException("User not found")));
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}

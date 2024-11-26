package com.bishevents.service.impl;

import com.bishevents.dto.response.UserResponseDTO;
import com.bishevents.dto.request.UserRequestDTO;
import com.bishevents.entity.User;
import com.bishevents.enums.Role;
import com.bishevents.exception.GeneralException;
import com.bishevents.mapper.UserMapper;
import com.bishevents.repository.UserRepository;
import com.bishevents.service.UserService;
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
    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
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
    public UserResponseDTO update(User user, String username, String email) {
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
    public UserResponseDTO get(Long id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow(() -> new GeneralException("User not found")));
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}

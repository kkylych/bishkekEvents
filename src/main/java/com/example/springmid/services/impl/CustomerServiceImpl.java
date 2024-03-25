package com.example.springmid.services.impl;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.reuest.CustomerRequestDTO;
import com.example.springmid.entities.Customer;
import com.example.springmid.exceptions.GeneralException;
import com.example.springmid.mappers.UserMapper;
import com.example.springmid.repositories.UserRepository;
import com.example.springmid.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public CustomerServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new GeneralException("Username already exists");
        }

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new GeneralException("Email already exists");
        }
        Customer user = userRepository.save(userMapper.toEntity(userRequestDTO));
        return userMapper.toDTO(user);
    }

    @Override
    public CustomerResponseDTO update(Long id, CustomerRequestDTO userRequestDTO) {
        Customer user = userRepository.findById(id).orElseThrow(() -> new GeneralException("User not found exception"));
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
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

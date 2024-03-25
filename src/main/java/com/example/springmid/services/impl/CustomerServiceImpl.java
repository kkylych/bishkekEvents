package com.example.springmid.services.impl;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.reuest.CustomerRequestDTO;
import com.example.springmid.entities.Customer;
import com.example.springmid.exceptions.GeneralException;
import com.example.springmid.mappers.CustomerMapper;
import com.example.springmid.repositories.UserRepository;
import com.example.springmid.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(UserRepository userRepository, CustomerMapper customerMapper) {
        this.userRepository = userRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new GeneralException("Username already exists");
        }

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new GeneralException("Email already exists");
        }
        Customer user = userRepository.save(customerMapper.toEntity(userRequestDTO));
        return customerMapper.toDTO(user);
    }

    @Override
    public CustomerResponseDTO update(Long id, CustomerRequestDTO userRequestDTO) {
        Customer user = userRepository.findById(id).orElseThrow(() -> new GeneralException("User not found exception"));
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        userRepository.save(user);
        return customerMapper.toDTO(user);
    }

    @Override
    public CustomerResponseDTO get(Long id) {
        return customerMapper.toDTO(userRepository.findById(id).orElseThrow(() -> new GeneralException("User not found")));
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
}

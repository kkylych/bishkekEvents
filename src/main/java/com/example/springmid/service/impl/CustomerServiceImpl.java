package com.example.springmid.service.impl;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.request.CustomerRequestDTO;
import com.example.springmid.entity.Customer;
import com.example.springmid.enums.Role;
import com.example.springmid.exception.GeneralException;
import com.example.springmid.mapper.CustomerMapper;
import com.example.springmid.repository.CustomerRepository;
import com.example.springmid.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO userRequestDTO) {
        if (customerRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new GeneralException("Username already exists");
        }

        if (customerRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new GeneralException("Email already exists");
        }
        Customer user = customerMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        customerRepository.save(user);

        return customerMapper.toDTO(user);
    }

    @Override
    public CustomerResponseDTO update(Customer customer, String username, String email) {
        if (customerRepository.existsByUsername(username)) {
            throw new GeneralException("User with such username exists");
        }

        if (customerRepository.existsByEmail(email)) {
            throw new GeneralException("User with such email exists");
        }

        customer.setUsername(username);
        customer.setEmail(email);
        customerRepository.save(customer);

        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerResponseDTO get(Long id) {
        return customerMapper.toDTO(customerRepository.findById(id).orElseThrow(() -> new GeneralException("User not found")));
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
}

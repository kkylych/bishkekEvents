package com.example.springmid.service;


import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.request.CustomerRequestDTO;
import com.example.springmid.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO update(Customer customer, String username, String email);

    CustomerResponseDTO get(Long id);

    List<CustomerResponseDTO> getAll();
}

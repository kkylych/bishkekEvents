package com.example.springmid.services;


import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.reuest.CustomerRequestDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO create(CustomerRequestDTO userRequestDTO);
    CustomerResponseDTO update(Long id, CustomerRequestDTO userRequestDTO);
    CustomerResponseDTO get(Long id);
    List<CustomerResponseDTO> getAll();
}

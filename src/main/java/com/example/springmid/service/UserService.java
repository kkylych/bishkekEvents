package com.example.springmid.service;


import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.request.UserRequestDTO;
import com.example.springmid.entity.User;

import java.util.List;

public interface UserService {
    CustomerResponseDTO create(UserRequestDTO userRequestDTO);

    CustomerResponseDTO update(User user, String username, String email);

    CustomerResponseDTO get(Long id);

    List<CustomerResponseDTO> getAll();
}

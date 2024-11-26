package com.bishevents.service;


import com.bishevents.dto.response.UserResponseDTO;
import com.bishevents.dto.request.UserRequestDTO;
import com.bishevents.entity.User;

import java.util.List;

public interface UserService {
    UserResponseDTO create(UserRequestDTO userRequestDTO);

    UserResponseDTO update(User user, String username, String email);

    UserResponseDTO get(Long id);

    List<UserResponseDTO> getAll();
}

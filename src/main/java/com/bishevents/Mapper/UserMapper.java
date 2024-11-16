package com.bishevents.Mapper;

import com.bishevents.DTO.UserDTO;
import com.bishevents.entity.User;

public class UserMapper {
    public static UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.id());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setRole(userDTO.role());
        return user;
    }
}

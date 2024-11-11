package com.bishevents.Mapper;

import com.bishevents.DTO.UserDTO;
import com.bishevents.entity.User_;

public class UserMapper {
    public static UserDTO toDto(User_ user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    public static User_ toEntity(UserDTO userDTO) {
        User_ user = new User_();
        user.setId(userDTO.id());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setRole(userDTO.role());
        return user;
    }
}

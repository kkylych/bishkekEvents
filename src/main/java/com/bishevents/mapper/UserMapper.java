package com.bishevents.mapper;

import com.bishevents.dto.response.UserResponseDTO;
import com.bishevents.dto.request.UserRequestDTO;
import com.bishevents.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toDTO(User user);
    User toEntity(UserRequestDTO userRequestDTO);
}

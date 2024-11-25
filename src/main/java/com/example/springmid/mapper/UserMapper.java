package com.example.springmid.mapper;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.request.UserRequestDTO;
import com.example.springmid.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    CustomerResponseDTO toDTO(User user);
    User toEntity(UserRequestDTO userRequestDTO);
}

package com.example.springmid.mapper;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.request.CustomerRequestDTO;
import com.example.springmid.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponseDTO toDTO(Customer user);
    Customer toEntity(CustomerRequestDTO userRequestDTO);
}

package com.example.springmid.mappers;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.reuest.CustomerRequestDTO;
import com.example.springmid.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponseDTO toDTO(Customer user);
    Customer toEntity(CustomerRequestDTO userRequestDTO);
}

package com.example.springmid.mappers;

import com.example.springmid.dto.response.OrderResponseDTO;
import com.example.springmid.dto.reuest.OrderRequestDTO;
import com.example.springmid.entities.Order;
import com.example.springmid.entities.Product;
import com.example.springmid.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Customer.class, Product.class})
public interface OrderMapper {

    Order toEntity(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO toDTO(Order order);
}

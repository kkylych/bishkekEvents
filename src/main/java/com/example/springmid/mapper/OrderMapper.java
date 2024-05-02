package com.example.springmid.mapper;

import com.example.springmid.dto.response.OrderResponseDTO;
import com.example.springmid.dto.request.OrderRequestDTO;
import com.example.springmid.entity.Order;
import com.example.springmid.entity.Product;
import com.example.springmid.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Customer.class, Product.class})
public interface OrderMapper {

    Order toEntity(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO toDTO(Order order);
}

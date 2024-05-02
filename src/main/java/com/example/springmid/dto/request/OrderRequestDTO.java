package com.example.springmid.dto.request;

import com.example.springmid.entity.Product;
import com.example.springmid.entity.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequestDTO {

    @NotNull(message = "Order must have user")
    private Customer user;

    @NotNull(message = "Order must have products")
    private List<Product> products;

    private LocalDateTime orderDate;

    @NotNull(message = "Price should not be null")
    private double totalPrice;
}

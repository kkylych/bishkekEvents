package com.example.springmid.dto.response;

import com.example.springmid.entity.Product;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime orderDate;
    private List<Product> products;
    private double totalPrice;
}

package com.example.springmid.dto.response;

import com.example.springmid.entity.Event;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingResponseDTO {
    private Long id;
    private LocalDateTime orderDate;
    private List<Event> products;
    private double totalPrice;
}

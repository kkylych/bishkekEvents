package com.example.springmid.dto.request;

import com.example.springmid.entity.Event;
import com.example.springmid.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingRequestDTO {

    @NotNull(message = "Booking must have user")
    private User user;

    @NotNull(message = "Booking must have products")
    private List<Event> products;

    private LocalDateTime orderDate;

    @NotNull(message = "Price should not be null")
    private double totalPrice;
}

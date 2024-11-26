package com.bishevents.dto.request;

import com.bishevents.entity.Event;
import com.bishevents.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingRequestDTO {

    @NotNull(message = "Booking must have user")
    private User user;

    @NotNull(message = "Booking must have products")
    private List<Event> events;

    private LocalDateTime eventDate;

    @NotNull(message = "Price should not be null")
    private double totalPrice;
}

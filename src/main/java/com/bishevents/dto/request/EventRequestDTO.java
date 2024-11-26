package com.bishevents.dto.request;

import com.bishevents.entity.Event;
import com.bishevents.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

public class EventRequestDTO {
    @NotNull(message = "ID cannot be null.")
    private Long id;

    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotBlank(message = "Category cannot be blank.")
    private String category;

    @NotBlank(message = "Description cannot be blank.")
    private String description;

    @NotNull(message = "Event date and time cannot be null.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDateTime;

    @NotBlank(message = "Location cannot be blank.")
    private String location;

    @NotNull(message = "Duration cannot be null.")
    @Positive(message = "Duration must be a positive number.")
    private Double duration;

    @NotNull(message = "Organizer ID cannot be null.")
    private Long organizerId;

//    public void setEvents(List<Event> products) {
//    }
//
//    public void setUser(User user) {
//    }
//
//    public void setTotalPrice(double v) {
//    }
//
//    public void setEventDate(LocalDateTime now) {
//    }
//
//    public short getTotalPrice() {
//    }
}

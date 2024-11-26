package com.bishevents.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponseDTO{
    private Long id;
    private String title;
    private String category;
    private String description;
    private LocalDateTime eventDateTime;
    private String location;
    private Double duration;
    private Long organizerId;

}

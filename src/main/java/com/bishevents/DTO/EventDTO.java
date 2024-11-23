package com.bishevents.DTO;

import java.time.LocalDateTime;

public record EventDTO(Long id, String title, String category, String description, LocalDateTime eventDateTime, String location, Double duration, Long organizerId) {

}

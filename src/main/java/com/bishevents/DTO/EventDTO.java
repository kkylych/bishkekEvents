package com.bishevents.DTO;

import java.time.LocalDateTime;

public record EventDTO(Long id, String title, String category, String description, LocalDateTime eventDateTime, String location, Double latitude, Double longitude, Long organizerId) {
}

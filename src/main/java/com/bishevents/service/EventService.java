package com.bishevents.service;

import com.bishevents.dto.response.EventResponseDTO;

import java.util.List;

public interface EventService {

    EventResponseDTO saveEvent(EventResponseDTO eventRequestDTO);
    EventResponseDTO getEventById(Long id);
    List<EventResponseDTO> getAllEvents();
    void deleteEvent(Long id);
}

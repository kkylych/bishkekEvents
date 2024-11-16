package com.bishevents.service;
import com.bishevents.DTO.EventDTO;
import com.bishevents.Mapper.EventMapper;
import com.bishevents.entity.Event;
import com.bishevents.entity.User;
import com.bishevents.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<EventDTO> getEventById(Long id) {
        return eventRepository.findById(id).map(EventMapper::toDto);
    }

    public EventDTO saveEvent(EventDTO eventDTO) {
        User organizer = new User(); // Здесь необходимо передать правильного организатора
        Event event = EventMapper.toEntity(eventDTO, organizer);
        Event savedEvent = eventRepository.save(event);
        return EventMapper.toDto(savedEvent);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}

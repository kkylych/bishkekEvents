package com.bishevents.service.impl;

import com.bishevents.dto.response.EventResponseDTO;
import com.bishevents.entity.Event;
import com.bishevents.exception.GeneralException;
import com.bishevents.mapper.EventMapper;
import com.bishevents.repository.EventRepository;
import com.bishevents.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public List<EventResponseDTO> getAllEvents() {
        Iterable<Event> eventsIterable = eventRepository.findAll();
        List<Event> eventsList = new ArrayList<>();
        eventsIterable.forEach(eventsList::add); // Convert Iterable to List

        return eventsList.stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventResponseDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Event not found with id: " + id));
        return eventMapper.toDTO(event);
    }

    @Override
    public EventResponseDTO saveEvent(EventResponseDTO eventRequestDTO) {
        Event event = eventMapper.toEntity(eventRequestDTO);
        event = eventRepository.save(event);
        return eventMapper.toDTO(event);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new GeneralException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }
}

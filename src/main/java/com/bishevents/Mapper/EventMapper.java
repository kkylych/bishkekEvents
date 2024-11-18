package com.bishevents.Mapper;

import com.bishevents.DTO.EventDTO;
import com.bishevents.entity.Event;
import com.bishevents.entity.User;

public class EventMapper {
    public static EventDTO toDto(Event event) {
        return new EventDTO(event.getId(), event.getTitle(), event.getCategory(), event.getDescription(),
                event.getEventDateTime(), event.getLocation(), event.getDuration(),
                event.getOrganizer().getId());
    }

    public static Event toEntity(EventDTO eventDTO, User organizer) {
        Event event = new Event();
        event.setId(eventDTO.id());
        event.setTitle(eventDTO.title());
        event.setCategory(eventDTO.category());
        event.setDescription(eventDTO.description());
        event.setEventDateTime(eventDTO.eventDateTime());
        event.setLocation(eventDTO.location());
        event.setDuration(eventDTO.duration());
        event.setOrganizer(organizer);
        return event;
    }
}

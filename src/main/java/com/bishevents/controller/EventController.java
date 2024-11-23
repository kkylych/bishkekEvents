package com.bishevents.controller;
import com.bishevents.DTO.EventDTO;
import com.bishevents.exception.ResourceNotFoundException;
import com.bishevents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        EventDTO event = eventService.getEventById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        EventDTO createdEvent = eventService.saveEvent(eventDTO);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Добавлен метод для фильтрации событий по категории
    @GetMapping("/category/{category}")
    public ResponseEntity<List<EventDTO>> getEventsByCategory(@PathVariable String category) {
        System.out.println("Запрос категории: " + category);
        // Получаем список событий по категории
        List<EventDTO> events = eventService.getEventsByCategory(category);

        if (events.isEmpty()) {
            // Если события не найдены, возвращаем 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Возвращаем события с статусом OK (200)
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
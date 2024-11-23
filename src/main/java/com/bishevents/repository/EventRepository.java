package com.bishevents.repository;
import com.bishevents.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCategory(String category);  // Этот метод будет фильтровать события по категории
}

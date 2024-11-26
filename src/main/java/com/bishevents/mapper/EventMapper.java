package com.bishevents.mapper;

import com.bishevents.dto.response.EventResponseDTO;
import com.bishevents.entity.Event;
import com.bishevents.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEntity(EventResponseDTO eventRequestDTO);

    EventResponseDTO toDTO(Event event);
}

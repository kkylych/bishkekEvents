package com.bishevents.mapper;

import com.bishevents.dto.response.BookingResponseDTO;
import com.bishevents.dto.request.BookingRequestDTO;
import com.bishevents.entity.Booking;
import com.bishevents.entity.Event;
import com.bishevents.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {User.class, Event.class})
public interface BookingMapper {

    Booking toEntity(BookingRequestDTO bookingRequestDTO);

    BookingResponseDTO toDTO(Booking booking);
}

package com.example.springmid.mapper;

import com.example.springmid.dto.response.BookingResponseDTO;
import com.example.springmid.dto.request.BookingRequestDTO;
import com.example.springmid.entity.Booking;
import com.example.springmid.entity.Event;
import com.example.springmid.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {User.class, Event.class})
public interface OrderMapper {

    Booking toEntity(BookingRequestDTO bookingRequestDTO);

    BookingResponseDTO toDTO(Booking booking);
}

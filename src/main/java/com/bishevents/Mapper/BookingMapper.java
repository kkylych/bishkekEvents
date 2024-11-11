package com.bishevents.Mapper;

import com.bishevents.DTO.BookingDTO;
import com.bishevents.entity.Booking;
import com.bishevents.entity.Event;
import com.bishevents.entity.User_;

public class BookingMapper {
    public static BookingDTO toDto(Booking booking) {
        return new BookingDTO(booking.getId(), booking.getUser().getId(), booking.getEvent().getId(), booking.getSeats());
    }

    public static Booking toEntity(BookingDTO bookingDTO, User_ user, Event event) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.id());
        booking.setUser(user);
        booking.setEvent(event);
        booking.setSeats(bookingDTO.seats());
        return booking;
    }
}

package com.bishevents.service;
import com.bishevents.DTO.BookingDTO;
import com.bishevents.Mapper.BookingMapper;
import com.bishevents.entity.Booking;
import com.bishevents.entity.Event;
import com.bishevents.entity.User_;
import com.bishevents.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<BookingDTO> getBookingById(Long id) {
        return bookingRepository.findById(id).map(BookingMapper::toDto);
    }

    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        User_ user = new User_(); // Здесь необходимо передать правильного пользователя
        Event event = new Event(); // Здесь необходимо передать правильное событие
        Booking booking = BookingMapper.toEntity(bookingDTO, user, event);
        Booking savedBooking = bookingRepository.save(booking);
        return BookingMapper.toDto(savedBooking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}

package com.bishevents.service.impl;

import com.bishevents.dto.response.BookingResponseDTO;
import com.bishevents.dto.request.BookingRequestDTO;
import com.bishevents.entity.Booking;
import com.bishevents.exception.GeneralException;
import com.bishevents.mapper.BookingMapper;
import com.bishevents.repository.BookingRepository;
import com.bishevents.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingResponseDTO create(BookingRequestDTO bookingRequestDTO) {
        Booking booking = bookingMapper.toEntity(bookingRequestDTO);
        bookingRepository.save(booking);
        return bookingMapper.toDTO(booking);
    }

    @Override
    public BookingResponseDTO update(Long id, BookingRequestDTO bookingRequestDTO) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new GeneralException("Booking not found"));
        booking.setOrderDate(bookingRequestDTO.getEventDate());
        booking.setProducts(bookingRequestDTO.getEvents());
        booking.setTotalPrice(bookingRequestDTO.getTotalPrice());

        return bookingMapper.toDTO(bookingRepository.save(booking));
    }

    @Override
    public BookingResponseDTO get(Long id) {
        return bookingMapper.toDTO(bookingRepository.findById(id).orElseThrow(() -> new GeneralException("Booking not found")));
    }

    @Override
    public List<BookingResponseDTO> getAll(Long id) {
        return bookingRepository.findAllByUserId(id).stream().map(bookingMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}

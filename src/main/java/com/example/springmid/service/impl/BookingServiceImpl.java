package com.example.springmid.service.impl;

import com.example.springmid.dto.response.BookingResponseDTO;
import com.example.springmid.dto.request.BookingRequestDTO;
import com.example.springmid.entity.Booking;
import com.example.springmid.exception.GeneralException;
import com.example.springmid.mapper.OrderMapper;
import com.example.springmid.repository.BookingRepository;
import com.example.springmid.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final OrderMapper orderMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, OrderMapper orderMapper) {
        this.bookingRepository = bookingRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public BookingResponseDTO create(BookingRequestDTO bookingRequestDTO) {
        Booking booking = orderMapper.toEntity(bookingRequestDTO);
        bookingRepository.save(booking);
        return orderMapper.toDTO(booking);
    }

    @Override
    public BookingResponseDTO update(Long id, BookingRequestDTO bookingRequestDTO) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new GeneralException("Booking not found"));
        booking.setOrderDate(bookingRequestDTO.getOrderDate());
        booking.setProducts(bookingRequestDTO.getProducts());
        booking.setTotalPrice(bookingRequestDTO.getTotalPrice());

        return orderMapper.toDTO(bookingRepository.save(booking));
    }

    @Override
    public BookingResponseDTO get(Long id) {
        return orderMapper.toDTO(bookingRepository.findById(id).orElseThrow(() -> new GeneralException("Booking not found")));
    }

    @Override
    public List<BookingResponseDTO> getAll(Long id) {
        return bookingRepository.findAllByUserId(id).stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}

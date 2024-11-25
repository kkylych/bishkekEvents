package com.example.springmid.service;

import com.example.springmid.dto.response.BookingResponseDTO;
import com.example.springmid.dto.request.BookingRequestDTO;

import java.util.List;

public interface BookingService {

    BookingResponseDTO create(BookingRequestDTO bookingRequestDTO);
    BookingResponseDTO update(Long id, BookingRequestDTO bookingRequestDTO);
    BookingResponseDTO get(Long id);
    List<BookingResponseDTO> getAll(Long id);
    void deleteBooking(Long id);

}

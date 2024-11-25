package com.example.springmid.customer;


import com.example.springmid.dto.request.BookingRequestDTO;
import com.example.springmid.dto.response.BookingResponseDTO;
import com.example.springmid.entity.Booking;
import com.example.springmid.entity.Product;
import com.example.springmid.entity.User;
import com.example.springmid.repository.BookingRepository;
import com.example.springmid.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl orderService;

    @Test
    void testCreateOrder() {
        User user = new User();
        user.setId(1L);

        Product dress = new Product();
        dress.setName("dress");

        Product shoes = new Product();
        shoes.setName("shoes");

        List<Product> products = new ArrayList<>();
        products.add(dress);
        products.add(shoes);

        BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
        bookingRequestDTO.setUser(user);
        bookingRequestDTO.setProducts(products);
        bookingRequestDTO.setTotalPrice(100.0);
        bookingRequestDTO.setOrderDate(LocalDateTime.now());

        when(bookingRepository.save(any())).thenReturn(new Booking());

        BookingResponseDTO createdOrder = orderService.create(bookingRequestDTO);

        verify(bookingRepository, times(1)).save(any());
        assertEquals(bookingRequestDTO.getTotalPrice(), createdOrder.getTotalPrice());
    }
    @Test
    void testGetOrder() {
        Booking booking = new Booking();
        booking.setId(1L);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        BookingResponseDTO fetchedOrder = orderService.get(1L);

        verify(bookingRepository, times(1)).findById(1L);
        assertEquals(booking.getId(), fetchedOrder.getId());
    }

    @Test
    void testUpdateOrder() {
        Booking booking = new Booking();
        booking.setId(1L);

        User user = new User();
        user.setId(1L);

        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
        bookingRequestDTO.setUser(user);
        bookingRequestDTO.setProducts(products);
        bookingRequestDTO.setTotalPrice(200.0);
        bookingRequestDTO.setOrderDate(LocalDateTime.now());

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any())).thenReturn(booking);

        BookingResponseDTO updatedOrder = orderService.update(1L, bookingRequestDTO);

        verify(bookingRepository, times(1)).findById(1L);
        verify(bookingRepository, times(1)).save(any());
        assertEquals(bookingRequestDTO.getTotalPrice(), updatedOrder.getTotalPrice());
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(bookingRepository).deleteById(1L);

        orderService.deleteOrder(1L);

        verify(bookingRepository, times(1)).deleteById(1L);
    }
}
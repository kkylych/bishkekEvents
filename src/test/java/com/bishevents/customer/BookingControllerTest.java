package com.bishevents.customer;

import com.bishevents.controller.BookingController;
import com.bishevents.dto.response.BookingResponseDTO;
import com.bishevents.dto.request.BookingRequestDTO;
import com.bishevents.service.UserService;
import com.bishevents.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookingController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrder() {
        BookingRequestDTO requestDTO = new BookingRequestDTO();

        BookingResponseDTO responseDTO = new BookingResponseDTO();

        when(bookingService.create(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<BookingResponseDTO> responseEntity = orderController.createBooking(requestDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    public void testUpdateOrder() {
        Long orderId = 1L;
        BookingRequestDTO requestDTO = new BookingRequestDTO();

        BookingResponseDTO responseDTO = new BookingResponseDTO();

        when(bookingService.update(orderId, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<BookingResponseDTO> responseEntity = orderController.updateBooking(orderId, requestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    public void testGetOrder() {
        Long orderId = 1L;
        BookingResponseDTO responseDTO = new BookingResponseDTO();

        when(bookingService.get(orderId)).thenReturn(responseDTO);

        ResponseEntity<BookingResponseDTO> responseEntity = orderController.getBooking(orderId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    public void testGetAllOrdersByUserId() {
        Long userId = 1L;
        List<BookingResponseDTO> responseDTOList = new ArrayList<>();

        when(bookingService.getAll(userId)).thenReturn(responseDTOList);

//        ResponseEntity<List<BookingResponseDTO>> responseEntity = orderController.getAllOrdersByUser(userId);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(responseDTOList, responseEntity.getBody());
    }

    @Test
    public void testDeleteOrder() {
        Long orderId = 1L;
        doNothing().when(bookingService).deleteBooking(orderId);

        ResponseEntity<Void> responseEntity = orderController.deleteBooking(orderId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(bookingService, times(1)).deleteBooking(orderId);
    }
}
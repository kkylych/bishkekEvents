package com.example.springmid.customer;

import com.example.springmid.controller.OrderController;
import com.example.springmid.dto.response.OrderResponseDTO;
import com.example.springmid.dto.request.OrderRequestDTO;
import com.example.springmid.service.CustomerService;
import com.example.springmid.service.OrderService;
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

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrder() {
        OrderRequestDTO requestDTO = new OrderRequestDTO();

        OrderResponseDTO responseDTO = new OrderResponseDTO();

        when(orderService.create(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<OrderResponseDTO> responseEntity = orderController.createOrder(requestDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    public void testUpdateOrder() {
        Long orderId = 1L;
        OrderRequestDTO requestDTO = new OrderRequestDTO();

        OrderResponseDTO responseDTO = new OrderResponseDTO();

        when(orderService.update(orderId, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<OrderResponseDTO> responseEntity = orderController.updateOrder(orderId, requestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    public void testGetOrder() {
        Long orderId = 1L;
        OrderResponseDTO responseDTO = new OrderResponseDTO();

        when(orderService.get(orderId)).thenReturn(responseDTO);

        ResponseEntity<OrderResponseDTO> responseEntity = orderController.getOrder(orderId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    public void testGetAllOrdersByUserId() {
        Long userId = 1L;
        List<OrderResponseDTO> responseDTOList = new ArrayList<>();

        when(orderService.getAll(userId)).thenReturn(responseDTOList);

//        ResponseEntity<List<OrderResponseDTO>> responseEntity = orderController.getAllOrdersByUser(userId);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(responseDTOList, responseEntity.getBody());
    }

    @Test
    public void testDeleteOrder() {
        Long orderId = 1L;
        doNothing().when(orderService).deleteOrder(orderId);

        ResponseEntity<Void> responseEntity = orderController.deleteOrder(orderId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(orderService, times(1)).deleteOrder(orderId);
    }
}
package com.example.springmid.customer;

import com.example.springmid.controller.CustomerController;
import com.example.springmid.dto.request.CustomerRequestDTO;
import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private CustomerService userService;

    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(userService);
    }

    @Test
    void createUser_ValidUser_ReturnsCreatedResponse() {
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setUsername("testuser");
        requestDTO.setEmail("test@example.com");
        requestDTO.setPassword("password");

        CustomerResponseDTO responseDTO = new CustomerResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setUsername(requestDTO.getUsername());
        responseDTO.setEmail(requestDTO.getEmail());

        when(userService.create(any(CustomerRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<CustomerResponseDTO> response = customerController.createUser(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void getAllUsers_ReturnsListOfUsers() {
        CustomerResponseDTO user = new CustomerResponseDTO();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        List<CustomerResponseDTO> userList = Collections.singletonList(user);

        when(userService.getAll()).thenReturn(userList);

        ResponseEntity<List<CustomerResponseDTO>> response = customerController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }
}

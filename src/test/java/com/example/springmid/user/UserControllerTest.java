package com.example.springmid.user;

import com.example.springmid.controllers.UserController;
import com.example.springmid.dto.reuest.CustomerRequestDTO;
import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.services.CustomerService;
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

class UserControllerTest {

    @Mock
    private CustomerService userService;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
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

        ResponseEntity<CustomerResponseDTO> response = userController.createUser(requestDTO);

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

        ResponseEntity<List<CustomerResponseDTO>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }
}

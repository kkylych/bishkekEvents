package com.bishevents.customer;

import com.bishevents.controller.UserController;
import com.bishevents.dto.response.UserResponseDTO;
import com.bishevents.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void getAllUsers_ReturnsListOfUsers() {
        UserResponseDTO user = new UserResponseDTO();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        List<UserResponseDTO> userList = Collections.singletonList(user);

        when(userService.getAll()).thenReturn(userList);

        ResponseEntity<List<UserResponseDTO>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }
}

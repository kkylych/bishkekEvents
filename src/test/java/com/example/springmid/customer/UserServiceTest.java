package com.example.springmid.customer;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.request.UserRequestDTO;
import com.example.springmid.entity.User;
import com.example.springmid.mapper.UserMapper;
import com.example.springmid.repository.UserRepository;
import com.example.springmid.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("testUser");
        requestDTO.setEmail("test@example.com");
        requestDTO.setPassword("password");

        User user = new User();
        user.setId(1L);
        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());

        CustomerResponseDTO expectedResponse = new CustomerResponseDTO();
        expectedResponse.setId(1L);
        expectedResponse.setUsername(user.getUsername());
        expectedResponse.setEmail(user.getEmail());

        when(userRepository.existsByUsername(requestDTO.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(userMapper.toEntity(requestDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(expectedResponse);

        CustomerResponseDTO actualResponse = userService.create(requestDTO);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }
}
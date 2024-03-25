package com.example.springmid.customer;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.reuest.CustomerRequestDTO;
import com.example.springmid.entities.Customer;
import com.example.springmid.mappers.CustomerMapper;
import com.example.springmid.repositories.CustomerRepository;
import com.example.springmid.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setUsername("testUser");
        requestDTO.setEmail("test@example.com");
        requestDTO.setPassword("password");

        Customer user = new Customer();
        user.setId(1L);
        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());

        CustomerResponseDTO expectedResponse = new CustomerResponseDTO();
        expectedResponse.setId(1L);
        expectedResponse.setUsername(user.getUsername());
        expectedResponse.setEmail(user.getEmail());

        when(customerRepository.existsByUsername(requestDTO.getUsername())).thenReturn(false);
        when(customerRepository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(customerMapper.toEntity(requestDTO)).thenReturn(user);
        when(customerRepository.save(user)).thenReturn(user);
        when(customerMapper.toDTO(user)).thenReturn(expectedResponse);

        CustomerResponseDTO actualResponse = userService.create(requestDTO);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }
}
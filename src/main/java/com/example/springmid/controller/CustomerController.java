package com.example.springmid.controller;

import com.example.springmid.dto.response.CustomerResponseDTO;
import com.example.springmid.dto.reuest.CustomerRequestDTO;
import com.example.springmid.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class CustomerController {
    private final CustomerService userService;

    public CustomerController(CustomerService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createUser(@RequestBody CustomerRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.create(userRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateUser(@PathVariable Long id, @RequestBody CustomerRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.update(id, userRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

}

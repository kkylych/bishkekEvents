package com.example.springmid.controller;

import com.example.springmid.dto.response.OrderResponseDTO;
import com.example.springmid.dto.request.OrderRequestDTO;
import com.example.springmid.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(orderService.create(orderRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(orderService.update(id, orderRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrdersByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getAll(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

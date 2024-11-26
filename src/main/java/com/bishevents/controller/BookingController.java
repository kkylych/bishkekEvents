package com.bishevents.controller;

import com.bishevents.dto.response.BookingResponseDTO;
import com.bishevents.dto.request.BookingRequestDTO;
import com.bishevents.entity.User;
import com.bishevents.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(
        name = "Controller for creating, getting, updating orders"
)
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @Operation(
            summary = "Booking creating"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) {
        return new ResponseEntity<>(bookingService.create(bookingRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Booking updating"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id,
                                                          @Valid @RequestBody BookingRequestDTO bookingRequestDTO) {
        return new ResponseEntity<>(bookingService.update(id, bookingRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Booking getting by id"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<BookingResponseDTO> getBooking(@PathVariable Long id) {
        return new ResponseEntity<>(bookingService.get(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Getting all orders of user"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookingsByUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(bookingService.getAll(user.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}

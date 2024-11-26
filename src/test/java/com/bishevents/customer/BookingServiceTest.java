//package com.bishevents.customer;
//
//
//import com.bishevents.dto.request.EventRequestDTO;
//import com.bishevents.dto.response.EventResponseDTO;
//import com.bishevents.entity.Event;
//import com.bishevents.entity.User;
//import com.bishevents.repository.EventRepository;
//import com.bishevents.service.impl.EventServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class EventServiceTest {
//
//    @Mock
//    private EventRepository eventRepository;
//
//    @InjectMocks
//    private EventServiceImpl eventService;
//
//    @Test
//    void testCreateEvent() {
//        User user = new User();
//        user.setId(1L);
//
//        Event dress = new Event();
//        dress.setName("dress");
//
//        Event shoes = new Event();
//        shoes.setName("shoes");
//
//        List<Event> products = new ArrayList<>();
//        products.add(dress);
//        products.add(shoes);
//
//        EventRequestDTO bookingRequestDTO = new EventRequestDTO();
//        bookingRequestDTO.setUser(user);
//        bookingRequestDTO.setEvents(products);
//        bookingRequestDTO.setTotalPrice(100.0);
//        bookingRequestDTO.setEventDate(LocalDateTime.now());
//
//        when(eventRepository.save(any())).thenReturn(new Event());
//
//        EventResponseDTO createdOrder = eventService.create(bookingRequestDTO);
//
//        verify(eventRepository, times(1)).save(any());
//        assertEquals(bookingRequestDTO.getTotalPrice(), createdOrder.getTotalPrice());
//    }
//    @Test
//    void testGetOrder() {
//        Event booking = new Event();
//        booking.setId(1L);
//
//        when(eventRepository.findById(1L)).thenReturn(Optional.of(booking));
//
//        EventResponseDTO fetchedOrder = eventService.get(1L);
//
//        verify(eventRepository, times(1)).findById(1L);
//        assertEquals(booking.getId(), fetchedOrder.getId());
//    }
//
//    @Test
//    void testUpdateOrder() {
//        Event booking = new Event();
//        booking.setId(1L);
//
//        User user = new User();
//        user.setId(1L);
//
//        List<Event> products = new ArrayList<>();
//        products.add(new Event());
//        products.add(new Event());
//
//        EventRequestDTO bookingRequestDTO = new EventRequestDTO();
//        bookingRequestDTO.setUser(user);
//        bookingRequestDTO.setEvents(products);
//        bookingRequestDTO.setTotalPrice(200.0);
//        bookingRequestDTO.setOrderDate(LocalDateTime.now());
//
//        when(eventRepository.findById(1L)).thenReturn(Optional.of(booking));
//        when(eventRepository.save(any())).thenReturn(booking);
//
//        EventResponseDTO updatedOrder = eventService.update(1L, bookingRequestDTO);
//
//        verify(eventRepository, times(1)).findById(1L);
//        verify(eventRepository, times(1)).save(any());
//        assertEquals(bookingRequestDTO.getTotalPrice(), updatedOrder.getTotalPrice());
//    }
//
//    @Test
//    void testDeleteOrder() {
//        doNothing().when(eventRepository).deleteById(1L);
//
//        eventService.deleteOrder(1L);
//
//        verify(eventRepository, times(1)).deleteById(1L);
//    }
//}
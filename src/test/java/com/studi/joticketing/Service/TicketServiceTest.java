package com.studi.joticketing.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.studi.joticketing.DTO.TicketRequest;
import com.studi.joticketing.DTO.TicketResponse;
import com.studi.joticketing.Repository.*;
import com.studi.joticketing.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketsRepository ticketsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlansRepository plansRepository;

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testBookTicket() {
        TicketRequest request = new TicketRequest();
        request.setPlan_id(Collections.singletonList(1));

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        Plans plan = new Plans();
        plan.setId(1L);

        Orders order = new Orders();
        order.setId(1L);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(plansRepository.findById(1L)).thenReturn(Optional.of(plan));
        when(ordersRepository.save(any(Orders.class))).thenReturn(order);
        when(ticketsRepository.save(any(Tickets.class))).thenReturn(new Tickets());

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testuser", null));

        List<TicketResponse> responses = ticketService.bookTicket(request);

        assertNotNull(responses);
        assertEquals(1, responses.size());
    }

    @Test
    public void testGetUserTickets() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        Tickets ticket = new Tickets();
        ticket.setPlanId(1L);

        Plans plan = new Plans();
        plan.setId(1L);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(ticketsRepository.findByUserId(1L)).thenReturn(Collections.singletonList(ticket));
        when(plansRepository.findById(1L)).thenReturn(Optional.of(plan));

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testuser", null));

        List<TicketResponse> responses = ticketService.getUserTickets();

        assertNotNull(responses);
        assertEquals(1, responses.size());
    }
}
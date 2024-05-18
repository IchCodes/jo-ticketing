package com.studi.joticketing.Service;

import com.studi.joticketing.DTO.TicketRequest;
import com.studi.joticketing.DTO.TicketResponse;
import com.studi.joticketing.Repository.TicketsRepository;
import com.studi.joticketing.Repository.UserRepository;
import com.studi.joticketing.model.Tickets;
import com.studi.joticketing.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketsRepository ticketsRepository;

    private final UserRepository userRepository;

    public TicketResponse bookTicket(TicketRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String ticketKey = UUID.randomUUID().toString();

        Tickets ticket = Tickets.builder()
                .ticket_key(ticketKey)
                .plan_id(request.getPlan_id())
                .user_id(user.getId())
                .qr_code(user.getUser_key() + "-" + ticketKey )
                .build();

        ticket = ticketsRepository.save(ticket);

        return new TicketResponse(ticket.getQr_code(), "message");
    }
}

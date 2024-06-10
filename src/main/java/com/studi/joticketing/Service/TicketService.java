package com.studi.joticketing.Service;

import com.studi.joticketing.DTO.TicketRequest;
import com.studi.joticketing.DTO.TicketResponse;
import com.studi.joticketing.Repository.OrdersRepository;
import com.studi.joticketing.Repository.PlansRepository;
import com.studi.joticketing.Repository.TicketsRepository;
import com.studi.joticketing.Repository.UserRepository;
import com.studi.joticketing.exception.InvalidRequestException;
import com.studi.joticketing.model.Orders;
import com.studi.joticketing.model.Plans;
import com.studi.joticketing.model.Tickets;
import com.studi.joticketing.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketsRepository ticketsRepository;

    private final UserRepository userRepository;

    private final PlansRepository plansRepository;

    private final OrdersRepository ordersRepository;

    public List<TicketResponse> bookTicket(TicketRequest request) {

        if (request == null || request.getPlan_id() == null || request.getPlan_id().isEmpty()) {
            throw new InvalidRequestException("Invalid request, plan_id is required to book ticket");
        }

        List<TicketResponse> responses = new ArrayList<>();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Plans plan = new Plans();

        Orders order = new Orders();
        order = ordersRepository.save(order);

        for (Integer plan_id : request.getPlan_id()) {
            String ticketKey = UUID.randomUUID().toString();
            plan = plansRepository.findById(Long.valueOf(plan_id)).orElseThrow(() -> new UsernameNotFoundException("Plan not found"));

            Tickets ticket = Tickets.builder()
                    .ticket_key(ticketKey)
                    .planId(plan_id)
                    .userId(user.getId())
                    .qr_code(user.getUser_key() + "." + ticketKey )
                    .orderId(order.getId())
                    .build();

            ticket = ticketsRepository.save(ticket);

            responses.add(new TicketResponse(ticket.getOrderId(),ticket.getId().toString(), user.getLastName() + " " + user.getFirstName(), plan.getPlan(), ticket.getQr_code(), "Ticket booked successfully"));
        }

        return responses;
    }

    public List<TicketResponse> getUserTickets() {
        List<TicketResponse> responses = new ArrayList<>();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Tickets> tickets = ticketsRepository.findByUserId(user.getId());

        for (Tickets ticket : tickets) {
            Plans plan = plansRepository.findById(ticket.getPlanId()).orElseThrow(() -> new UsernameNotFoundException("Plan not found"));
            responses.add(new TicketResponse(ticket.getOrderId(),ticket.getId().toString(), user.getLastName() + " " + user.getFirstName(), plan.getPlan(), ticket.getQr_code(), "Ticket booked successfully"));
        }

        return responses;
    }

    public Map<String, Long> getSales() {
        List<Tickets> tickets = ticketsRepository.findAll();
        return tickets.stream()
                .collect(Collectors.groupingBy(ticket -> {
                    Plans plan = plansRepository.findById(ticket.getPlanId())
                            .orElseThrow(() -> new UsernameNotFoundException("Plan not found"));
                    return plan.getPlan();
                }, Collectors.counting()));
    }
}

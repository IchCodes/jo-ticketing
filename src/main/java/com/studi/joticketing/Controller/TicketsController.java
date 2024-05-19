package com.studi.joticketing.Controller;

import com.studi.joticketing.DTO.TicketRequest;
import com.studi.joticketing.DTO.TicketResponse;
import com.studi.joticketing.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketsController {

    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<List<TicketResponse>> bookTicket(@RequestBody TicketRequest request) {
        List<TicketResponse> responses = ticketService.bookTicket(request);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user")
    public ResponseEntity<List<TicketResponse>> getUserTickets() {
        List<TicketResponse> responses = ticketService.getUserTickets();
        return ResponseEntity.ok(responses);
    }
}

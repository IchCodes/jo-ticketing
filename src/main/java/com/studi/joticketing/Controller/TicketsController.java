package com.studi.joticketing.Controller;

import com.studi.joticketing.DTO.TicketRequest;
import com.studi.joticketing.DTO.TicketResponse;
import com.studi.joticketing.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketsController {

    private final TicketService ticketService;
    @PostMapping("/book")
    public ResponseEntity<TicketResponse> bookTicket(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.bookTicket(request));
    }
}

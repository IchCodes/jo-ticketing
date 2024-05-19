package com.studi.joticketing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class TicketResponse {
    private final String ticket_id;
    private final String owner;
    private final String plan;
    private final String qr_code;
    private final String message;
}

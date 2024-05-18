package com.studi.joticketing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class TicketResponse {
    private final String qr_code;
    private final String message;
}

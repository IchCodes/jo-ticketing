package com.studi.joticketing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticket_key;

    @Column(name = "plan_id")
    private long planId;

    @Column(name = "user_id")
    private long userId;

    private String qr_code;

    @Column(name = "order_id")
    private long orderId;
}

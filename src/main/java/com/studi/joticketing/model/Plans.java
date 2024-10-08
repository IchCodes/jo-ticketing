package com.studi.joticketing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plan;

    private int ticket_quantity;

    private int price;

    private String image_url;

    public void setPlan(String plan) {
        if (plan != null && !plan.isEmpty()) {
            this.plan = plan.substring(0, 1).toUpperCase() + plan.substring(1).toLowerCase();
        } else {
            this.plan = plan;
        }
    }
}

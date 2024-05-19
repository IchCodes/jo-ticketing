package com.studi.joticketing.Repository;

import com.studi.joticketing.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketsRepository extends JpaRepository<Tickets, Long> {
    List<Tickets> findByUserId(Long user_Id);
}

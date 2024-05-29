package com.studi.joticketing.Service;

import com.studi.joticketing.Repository.PlansRepository;
import com.studi.joticketing.Repository.TicketsRepository;
import com.studi.joticketing.model.Plans;
import com.studi.joticketing.model.Tickets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlansRepository planRepository;
    private final TicketsRepository ticketsRepository;


    public List<Plans> getAllPlans() {
        return planRepository.findAll();
    }

    public void deletePlanById(Long id) {
        // Get all tickets that reference the plan
        List<Tickets> tickets = ticketsRepository.findByPlanId(id);

        // Delete all tickets that reference the plan
        ticketsRepository.deleteAll(tickets);

        // Delete the plan
        planRepository.deleteById(id);
    }
}

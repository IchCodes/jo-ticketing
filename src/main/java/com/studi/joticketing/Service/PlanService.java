package com.studi.joticketing.Service;

import com.studi.joticketing.DTO.StandardResponse;
import com.studi.joticketing.Repository.PlansRepository;
import com.studi.joticketing.Repository.TicketsRepository;
import com.studi.joticketing.model.Plans;
import com.studi.joticketing.model.Tickets;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlansRepository planRepository;
    private final TicketsRepository ticketsRepository;


    public List<Plans> getAllPlans() {
        return planRepository.findAll();
    }

    public StandardResponse deletePlanById(Long id) {
        Optional<Plans> optionalPlan = planRepository.findById(id);
        if (optionalPlan.isEmpty()) {
            return new StandardResponse("Plan not found");
        }

        List<Tickets> tickets = ticketsRepository.findByPlanId(id);
        ticketsRepository.deleteAll(tickets);
        planRepository.deleteById(id);

        return new StandardResponse("Plan and associated tickets deleted successfully");
    }

    public StandardResponse addPlan(Plans plan, boolean isNew) {
        planRepository.save(plan);
        if (isNew) {
            return new StandardResponse("Plan added successfully");
        } else {
            return new StandardResponse("Plan updated successfully");
        }
    }
}

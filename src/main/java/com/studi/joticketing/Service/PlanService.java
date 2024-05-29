package com.studi.joticketing.Service;

import com.studi.joticketing.Repository.PlansRepository;
import com.studi.joticketing.model.Plans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlansRepository planRepository;


    public List<Plans> getAllPlans() {
        return planRepository.findAll();
    }

    public void deletePlanById(Long id) {
        planRepository.deleteById(id);
    }
}

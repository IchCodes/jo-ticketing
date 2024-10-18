package com.studi.joticketing.Service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.studi.joticketing.DTO.StandardResponse;
import com.studi.joticketing.Repository.PlansRepository;
import com.studi.joticketing.Repository.TicketsRepository;
import com.studi.joticketing.Service.PlanService;
import com.studi.joticketing.exception.EntityNotFoundException;
import com.studi.joticketing.model.Plans;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PlanServiceTest {

    @Mock
    private PlansRepository plansRepository;

    @Mock
    private TicketsRepository ticketsRepository;

    @InjectMocks
    private PlanService planService;

    @Test
    public void testGetAllPlans() {
        Plans plan = new Plans();
        when(plansRepository.findAll()).thenReturn(Collections.singletonList(plan));

        List<Plans> plans = planService.getAllPlans();

        assertNotNull(plans);
        assertEquals(1, plans.size());
    }

    @Test
    public void testDeletePlanById() {
        Plans plan = new Plans();
        when(plansRepository.findById(1L)).thenReturn(Optional.of(plan));

        planService.deletePlanById(1L);

        verify(plansRepository).deleteById(1L);
    }

    @Test
    public void testAddPlan() {
        Plans plan = new Plans();
        when(plansRepository.save(plan)).thenReturn(plan);

        StandardResponse response = planService.addPlan(plan, true);

        assertNotNull(response);
        assertEquals("Plan added successfully", response.getMessage());
    }
}
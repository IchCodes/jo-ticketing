package com.studi.joticketing.Controller;

import com.studi.joticketing.DTO.StandardResponse;
import com.studi.joticketing.Service.PlanService;
import com.studi.joticketing.model.Plans;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {

        private final PlanService planService;

        @GetMapping("/all")
        public List<Plans> getAllPlans() {
            return planService.getAllPlans();
        }

        @PostMapping("/add")
        public ResponseEntity<Plans> addPlan(@RequestBody Plans plan) {
            Plans newPlan = planService.addPlan(plan);
            return ResponseEntity.ok(newPlan);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<StandardResponse> deletePlanById(@PathVariable Long id) {
            StandardResponse response = planService.deletePlanById(id);
            return ResponseEntity.ok(response);
        }
}

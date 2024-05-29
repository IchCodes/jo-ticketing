package com.studi.joticketing.Controller;

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

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deletePlanById(@PathVariable Long id) {
            planService.deletePlanById(id);
            return ResponseEntity.ok("Plan deleted successfully");
        }
}

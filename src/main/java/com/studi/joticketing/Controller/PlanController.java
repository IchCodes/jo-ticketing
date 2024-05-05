package com.studi.joticketing.Controller;

import com.studi.joticketing.Service.PlanService;
import com.studi.joticketing.model.Plans;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

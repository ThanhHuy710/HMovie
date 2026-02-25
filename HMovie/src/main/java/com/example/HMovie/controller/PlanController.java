package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.PlanRequest;
import com.example.HMovie.dto.response.PlanResponse;
import com.example.HMovie.service.PlanService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PlanController {
    PlanService planService;

    @GetMapping
    ApiResponse<List<PlanResponse>> getAllPlans() {
        return ApiResponse.<List<PlanResponse>>builder()
                .result(planService.getAllPlans())
                .build();
    }
    @GetMapping("/{planId}")
    ApiResponse<PlanResponse> getPlanById(@PathVariable("planId") String planId) {
        return ApiResponse.<PlanResponse>builder()
                .result(planService.getPlanById(planId))
                .build();
    }


    @PostMapping
    ApiResponse<PlanResponse> createPlan(@RequestBody @Valid PlanRequest request) {
        return ApiResponse.<PlanResponse>builder()
                .result(planService.createPlan(request))
                .build();
    }

    @PutMapping("/{planId}")
    ApiResponse<PlanResponse> updatePlan(@PathVariable("planId") String planId, @RequestBody @Valid PlanRequest request) {
        return ApiResponse.<PlanResponse>builder()
                .result(planService.updatePlan(planId, request))
                .build();
    }

    @DeleteMapping("/{planId}")
    ApiResponse<String> deletePlan(@PathVariable("planId") String planId) {
        planService.deletePlan(planId);
        return ApiResponse.<String>builder()
                .result("Plan has been deleted")
                .build();
    }
}

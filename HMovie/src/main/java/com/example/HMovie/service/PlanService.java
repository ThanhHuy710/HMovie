package com.example.HMovie.service;

import com.example.HMovie.dto.request.PlanRequest;
import com.example.HMovie.dto.response.PlanResponse;
import com.example.HMovie.entity.Plan;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.PlanMapper;
import com.example.HMovie.repository.PlanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PlanService {
    PlanRepository planRepository;
    PlanMapper planMapper;

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "plan_list")
    public List<PlanResponse> getAllPlans() {
        log.info("getAllPlans: Fetching from Database");
        return planRepository.findAll().stream()
                .map(planMapper::toPlanResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "plan_detail", key = "#planId")
    public PlanResponse getPlanById(String planId) {
        log.info("getPlanById: Fetching from Database for id {}", planId);
        return planMapper.toPlanResponse(planRepository.findById(planId)
                .orElseThrow(() -> new AppException(ErrorCode.PLAN_NOT_FOUND)));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "plan_list", allEntries = true)
    public PlanResponse createPlan(PlanRequest request) {
        log.info("createPlan: Creating new plan");
        Plan plan = planMapper.toPlan(request);
        return planMapper.toPlanResponse(planRepository.save(plan));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(
            evict = {
                    @CacheEvict(value = "plan_list", allEntries = true),
                    @CacheEvict(value = "plan_detail", key = "#planId")
            }
    )
    public PlanResponse updatePlan(String planId, PlanRequest request) {
        log.info("updatePlan: Updating plan {}", planId);
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new AppException(ErrorCode.PLAN_NOT_FOUND));
        
        planMapper.updatePlan(plan, request);
        return planMapper.toPlanResponse(planRepository.save(plan));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(
            evict = {
                    @CacheEvict(value = "plan_list", allEntries = true),
                    @CacheEvict(value = "plan_detail", key = "#planId")
            }
    )
    public void deletePlan(String planId) {
        log.info("deletePlan: Deleting plan {}", planId);
        if (!planRepository.existsById(planId)) {
            throw new AppException(ErrorCode.PLAN_NOT_FOUND);
        }
        planRepository.deleteById(planId);
    }

}

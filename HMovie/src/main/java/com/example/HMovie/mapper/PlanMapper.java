package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.PlanRequest;
import com.example.HMovie.dto.response.PlanResponse;
import com.example.HMovie.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlanMapper {
    Plan toPlan(PlanRequest request);
    PlanResponse toPlanResponse(Plan plan);
    void updatePlan(@MappingTarget Plan plan, PlanRequest request);
}

package com.example.HMovie.repository;

import com.example.HMovie.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String> {
}

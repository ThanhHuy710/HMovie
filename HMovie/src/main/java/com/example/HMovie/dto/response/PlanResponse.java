package com.example.HMovie.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlanResponse {
    String planId;
    String name;
    BigDecimal price;
    Integer durationDays;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

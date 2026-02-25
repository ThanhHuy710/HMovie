package com.example.HMovie.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String invoiceId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    Plan plan;

    BigDecimal totalPrice;
    String paymentMethod;
    String status;
    LocalDate startDate;
    LocalDate endDate;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;
}

package com.example.HMovie.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String cartId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    Plan plan;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime addedAt;
}

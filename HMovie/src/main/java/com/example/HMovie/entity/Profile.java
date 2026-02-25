package com.example.HMovie.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String profileId;
    String phoneNumber;
    Boolean gender;
    String avatar;
    LocalDateTime expertTimePlan;
    // UserId from keycloak
    @Column(unique = true)
    String userId;
    String email;
    String username;
    String firstName;
    String lastName;
    LocalDate dob;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createAt;
    @UpdateTimestamp
    LocalDateTime updateAt;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Cart> carts;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Favorite> favorites;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Feedback> feedbacks;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Invoice> invoices;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    List<View> views;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    Plan plan;
}

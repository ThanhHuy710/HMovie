package com.example.HMovie.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String profileId;

    // UserId from keycloak
    @Column(unique = true)
    String userId;
    String email;
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
}

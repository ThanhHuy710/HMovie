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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"profile_id", "movie_id"})
})
public class Favorite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String favoriteId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime addedAt;
}

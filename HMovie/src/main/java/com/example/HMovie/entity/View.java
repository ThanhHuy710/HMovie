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
public class View implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String viewId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime viewedAt;
    
    Integer progress;
}

package com.example.HMovie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Episode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String episodeId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "movie_id")
    Movie movie;

    String episodeName;
    String videoURL;
    String subURL;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
}

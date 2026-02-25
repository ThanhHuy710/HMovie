package com.example.HMovie.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String movieId;

    String title;
    
    @Builder.Default
    Integer season = 0;
    
    String posterURL;
    
    @Column(columnDefinition = "TEXT")
    String description;
    
    Integer year;
    String country;
    String duration;
    String posterVideoURL;
    
    @Column(columnDefinition = "TEXT")
    String actor;
    
    Boolean isSeries;
    String director;
    String ageRating;
    
    @Builder.Default
    Long viewCount = 0L;
    
    @Builder.Default
    BigDecimal averageRating = BigDecimal.ZERO;

    @Builder.Default
    Long favoriteCount = 0L;

    String originalName;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Episode> episodes;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Favorite> favorites;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Feedback> feedbacks;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<View> views;
}

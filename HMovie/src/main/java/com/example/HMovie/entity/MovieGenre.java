package com.example.HMovie.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class MovieGenre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String movieGenreId;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    Genre genre;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;
}

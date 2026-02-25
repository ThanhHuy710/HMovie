package com.example.HMovie.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    String movieId;
    String title;
    Integer season;
    String posterURL;
    String description;
    Integer year;
    String country;
    String duration;
    String posterVideoURL;
    String actor;
    Boolean isSeries;
    String director;
    String ageRating;
    Integer viewCount;
    BigDecimal averageRating;
    String originalName;
    Integer favoriteCount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<EpisodeResponse> episodes;
}

package com.example.HMovie.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequest {
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
    String originalName;
}

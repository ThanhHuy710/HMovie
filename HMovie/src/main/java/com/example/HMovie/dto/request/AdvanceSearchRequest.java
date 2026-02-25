package com.example.HMovie.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvanceSearchRequest {
    Boolean isSeries;
    String ageRating;
    String genres;
    String country;
    Integer year;
}

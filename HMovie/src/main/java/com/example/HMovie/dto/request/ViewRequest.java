package com.example.HMovie.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewRequest {
    String movieId;
    String episodeId;
    String profileId;
    Integer progress;
}

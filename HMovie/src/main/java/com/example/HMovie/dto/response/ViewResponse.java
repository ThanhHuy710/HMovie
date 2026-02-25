package com.example.HMovie.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewResponse {
    String viewId;
    String movieId;
    String episodeId;
    String profileId;
    LocalDateTime viewedAt;
    Integer progress;
}

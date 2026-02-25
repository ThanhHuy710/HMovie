package com.example.HMovie.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EpisodeResponse {
    String episodeId;
    String movieId;
    String episodeName;
    String videoURL;
    String subURL;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

package com.example.HMovie.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackResponse {
    String feedbackId;
    String profileId;
    String movieId;
    String comment;
    Integer rating;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

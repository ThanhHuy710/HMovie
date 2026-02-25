package com.example.HMovie.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreResponse {
    String genreId;
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

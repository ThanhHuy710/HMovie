package com.example.HMovie.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileRequest {
    String firstName;
    String lastName;
    String phoneNumber;
    Boolean gender;
    String interest;
    String avatar;
    LocalDateTime expertTimePlan;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dob;
}

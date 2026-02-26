package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.identity.TokenExchangeResponse;
import com.example.HMovie.dto.request.LoginRequest;
import com.example.HMovie.dto.request.ProfileRequest;
import com.example.HMovie.dto.request.RegistrationRequest;
import com.example.HMovie.dto.response.ProfileResponse;
import com.example.HMovie.service.ProfileService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProfileController {
    ProfileService profileService;

    @PostMapping("/register")
    ApiResponse<ProfileResponse> register(@RequestBody @Valid RegistrationRequest request) {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.register(request))
                .build();
    }
    @PostMapping("/login")
    ApiResponse<TokenExchangeResponse> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.<TokenExchangeResponse>builder()
                .result(profileService.login(request))
                .build();
    }
    @GetMapping("/profiles")
    ApiResponse<List<ProfileResponse>> getAllProfiles() {
        return ApiResponse.<List<ProfileResponse>>builder()
                .result(profileService.getAllProfiles())
                .build();
    }

    @GetMapping("/{profileId}")
    ApiResponse<ProfileResponse> getProfileById(@PathVariable @Valid String profileId) {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.getProfileById(profileId))
                .build();
    }
    @GetMapping("/my-profile")
    ApiResponse<ProfileResponse> getProfile() {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.getMyProfile())
                .build();
    }
    @PutMapping("/{profileId}")
    ApiResponse<ProfileResponse> updateMyProfile(@RequestBody @Valid ProfileRequest request) {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.updateMyProfile(request))
                .build();
    }

    @DeleteMapping("/{profileId}")
    ApiResponse<String> deleteProfile(@PathVariable("profileId") String profileId) {
        profileService.deleteProfile(profileId);
        return ApiResponse.<String>builder()
                .result("Profile and Keycloak User have been deleted")
                .build();
    }
}

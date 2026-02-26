package com.example.HMovie.service;

import com.example.HMovie.dto.identity.Credential;
import com.example.HMovie.dto.identity.TokenExchangeResponse;
import com.example.HMovie.dto.identity.UserCreationParam;
import com.example.HMovie.dto.request.LoginRequest;
import com.example.HMovie.dto.request.ProfileRequest;
import com.example.HMovie.dto.request.RegistrationRequest;
import com.example.HMovie.dto.response.ProfileResponse;
import com.example.HMovie.entity.Profile;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.exception.ErrorNormalizer;
import com.example.HMovie.mapper.ProfileMapper;
import com.example.HMovie.repository.IdentityClient;
import com.example.HMovie.repository.ProfileRepository;
import feign.FeignException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileService {
    ProfileRepository profileRepository;
    ProfileMapper profileMapper;
    IdentityClient identityClient;
    ErrorNormalizer errorNormalizer;

    @Value("${idp.client-id}")
    @NonFinal
    String clientId;

    @Value("${idp.client-secret}")
    @NonFinal
    String clientSecret;

    @PreAuthorize("hasRole('ADMIN')")
    @Cacheable(value = "ProfileRepository")
    public List<ProfileResponse> getAllProfiles() {
        var profiles = profileRepository.findAll();
        return profiles.stream().map(profileMapper::toProfileResponse).toList();
    }

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "ProfileRepository")
    public ProfileResponse getMyProfile() {
        log.info("getMyProfile đã được gọi");
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        var profile = profileRepository.findByUserId(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return profileMapper.toProfileResponse(profile);
    }

    @PreAuthorize("hasRole('USER')")
    @CacheEvict(value = "ProfileRepository", allEntries = true)
    public ProfileResponse updateMyProfile(ProfileRequest request) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        var profile = profileRepository.findByUserId(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        
        // 1. Update DB
        profileMapper.updateProfile(profile, request);
        profile = profileRepository.save(profile);

        // 2. Update Keycloak
        try {
            String token = getAdminToken();

            UserCreationParam updateParam = UserCreationParam.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .build();

            identityClient.updateUser("Bearer " + token, userId, updateParam);
            log.info("Updated user {} in Keycloak", userId);
        } catch (Exception e) {
            log.error("Failed to update user {} in Keycloak: {}", userId, e.getMessage());
        }
        
        return profileMapper.toProfileResponse(profile);
    }

    public ProfileResponse register(RegistrationRequest request) {
        try {
            String token = getAdminToken();

            var creationResponse = identityClient.createUser(
                    "Bearer " + token,
                    UserCreationParam.builder()
                            .username(request.getUsername())
                            .firstName(request.getFirstName())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .enabled(true)
                            .emailVerified(false)
                            .credentials(List.of(Credential.builder()
                                    .type("password")
                                    .temporary(false)
                                    .value(request.getPassword())
                                    .build()))
                            .build());

            String userId = extractUserId(creationResponse);
            log.info("UserId {}", userId);

            var profile = profileMapper.toProfile(request);
            profile.setUserId(userId);

            profile = profileRepository.save(profile);

            return profileMapper.toProfileResponse(profile);
        } catch (FeignException exception) {
            throw errorNormalizer.handleKeyCloakException(exception);
        }
    }

    public TokenExchangeResponse login(@Valid LoginRequest request) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "password");
            params.put("client_id", clientId);
            params.put("client_secret", clientSecret);
            params.put("username", request.getUsername());
            params.put("password", request.getPassword());
            params.put("scope", "openid");

            return identityClient.exchangeToken(params);
        } catch (FeignException exception) {
            throw errorNormalizer.handleKeyCloakException(exception);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "ProfileRepository", allEntries = true)
    public void deleteProfile(String profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        String userId = profile.getUserId();

        try {
            String token = getAdminToken();
            identityClient.deleteUser("Bearer " + token, userId);
            log.info("Deleted user {} in Keycloak", userId);
        } catch (Exception e) {
            log.warn("Failed to delete user {} in Keycloak: {}", userId, e.getMessage());
        }

        profileRepository.delete(profile);
        log.info("Deleted profile {}", profileId);
    }

    private String getAdminToken() {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("scope", "openid");

        var token = identityClient.exchangeToken(params);
        return token.getAccessToken();
    }

    private String extractUserId(ResponseEntity<?> response) {
        String location = response.getHeaders().get("Location").getFirst();
        String[] splitedStr = location.split("/");
        return splitedStr[splitedStr.length - 1];
    }

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "ProfileRepository", key = "#profileId")
    public ProfileResponse getProfileById(@Valid String profileId) {
        var profile = profileRepository.findById(profileId).orElseThrow(
                () -> new AppException(ErrorCode.PROFILE_NOT_FOUND));
        return profileMapper.toProfileResponse(profile);
    }
}

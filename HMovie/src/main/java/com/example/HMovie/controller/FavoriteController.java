package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.FavoriteRequest;
import com.example.HMovie.dto.response.FavoriteResponse;
import com.example.HMovie.service.FavoriteService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FavoriteController {
    FavoriteService favoriteService;

    @GetMapping
    ApiResponse<List<FavoriteResponse>> getAllFavorites() {
        return ApiResponse.<List<FavoriteResponse>>builder()
                .result(favoriteService.getAllFavorites())
                .build();
    }
    @GetMapping("profile/{profileId}")
    ApiResponse<List<FavoriteResponse>> getFavoritesByProfileId(@PathVariable("profileId") String profile) {
        return ApiResponse.<List<FavoriteResponse>>builder()
                .result(favoriteService.getFavoritesByProfileId(profile))
                .build();
    }

    @GetMapping("profileAndMovie/{profileId}/{movieId}")
    ApiResponse<FavoriteResponse> getFavoriteByProfileIdAndMovieId(
            @PathVariable("profileId") String profileId,
            @PathVariable("movieId") String movieId) {
        return ApiResponse.<FavoriteResponse>builder()
                .result(favoriteService.getFavoriteByProfileIdAndMovieId(profileId,movieId))
                .build();
    }
    @GetMapping("/{favoriteId}")
    ApiResponse<FavoriteResponse> getFavoriteById(@PathVariable("favoriteId") String favoriteId) {
        return ApiResponse.<FavoriteResponse>builder()
                .result(favoriteService.getFavoriteById(favoriteId))
                .build();
    }

    @PostMapping
    ApiResponse<FavoriteResponse> createFavorite(@RequestBody @Valid FavoriteRequest request) {
        return ApiResponse.<FavoriteResponse>builder()
                .result(favoriteService.createFavorite(request))
                .build();
    }

    @PutMapping("/{favoriteId}")
    ApiResponse<FavoriteResponse> updateFavorite(@PathVariable("favoriteId") String favoriteId, @RequestBody @Valid FavoriteRequest request) {
        return ApiResponse.<FavoriteResponse>builder()
                .result(favoriteService.updateFavorite(favoriteId, request))
                .build();
    }

    @DeleteMapping("/{favoriteId}")
    ApiResponse<String> deleteFavorite(@PathVariable("favoriteId") String favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return ApiResponse.<String>builder()
                .result("Favorite has been deleted")
                .build();
    }
}

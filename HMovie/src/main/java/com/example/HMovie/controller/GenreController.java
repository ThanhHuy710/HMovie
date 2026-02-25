package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.GenreRequest;
import com.example.HMovie.dto.response.GenreResponse;
import com.example.HMovie.service.GenreService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GenreController {
    GenreService genreService;

    @GetMapping
    ApiResponse<List<GenreResponse>> getAllGenres() {
        return ApiResponse.<List<GenreResponse>>builder()
                .result(genreService.getAllGenres())
                .build();
    }

    @GetMapping("/{genreId}")
    ApiResponse<GenreResponse> getGenreById(@PathVariable("genreId") String genreId) {
        return ApiResponse.<GenreResponse>builder()
                .result(genreService.getGenreById(genreId))
                .build();
    }

    @PostMapping
    ApiResponse<GenreResponse> createGenre(@RequestBody @Valid GenreRequest request) {
        return ApiResponse.<GenreResponse>builder()
                .result(genreService.createGenre(request))
                .build();
    }

    @PutMapping("/{genreId}")
    ApiResponse<GenreResponse> updateGenre(@PathVariable("genreId") String genreId, @RequestBody @Valid GenreRequest request) {
        return ApiResponse.<GenreResponse>builder()
                .result(genreService.updateGenre(genreId, request))
                .build();
    }

    @DeleteMapping("/{genreId}")
    ApiResponse<String> deleteGenre(@PathVariable("genreId") String genreId) {
        genreService.deleteGenre(genreId);
        return ApiResponse.<String>builder()
                .result("Genre has been deleted")
                .build();
    }
}

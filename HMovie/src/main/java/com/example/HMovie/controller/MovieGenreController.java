package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.MovieGenreRequest;
import com.example.HMovie.dto.response.MovieGenreResponse;
import com.example.HMovie.service.MovieGenreService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-genres")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MovieGenreController {
    MovieGenreService movieGenreService;

    @GetMapping
    ApiResponse<List<MovieGenreResponse>> getAllMovieGenres() {
        return ApiResponse.<List<MovieGenreResponse>>builder()
                .result(movieGenreService.getAllMovieGenres())
                .build();
    }

    @GetMapping("/{movieGenreId}")
    ApiResponse<MovieGenreResponse> getMovieGenreById(@PathVariable("movieGenreId") String movieGenreId) {
        return ApiResponse.<MovieGenreResponse>builder()
                .result(movieGenreService.getMovieGenreById(movieGenreId))
                .build();
    }

    @PostMapping
    ApiResponse<MovieGenreResponse> createMovieGenre(@RequestBody @Valid MovieGenreRequest request) {
        return ApiResponse.<MovieGenreResponse>builder()
                .result(movieGenreService.createMovieGenre(request))
                .build();
    }

    @PutMapping("/{movieGenreId}")
    ApiResponse<MovieGenreResponse> updateMovieGenre(@PathVariable("movieGenreId") String movieGenreId, @RequestBody @Valid MovieGenreRequest request) {
        return ApiResponse.<MovieGenreResponse>builder()
                .result(movieGenreService.updateMovieGenre(movieGenreId, request))
                .build();
    }

    @DeleteMapping("/{movieGenreId}")
    ApiResponse<String> deleteMovieGenre(@PathVariable("movieGenreId") String movieGenreId) {
        movieGenreService.deleteMovieGenre(movieGenreId);
        return ApiResponse.<String>builder()
                .result("Movie Genre relation has been deleted")
                .build();
    }
    @DeleteMapping("/movie/{movieId}")
    ApiResponse<String> deleteMovieGenreByMovieId(@PathVariable("movieId") String movieId) {
        movieGenreService.deleteMovieGenreByMovieId(movieId);
        return ApiResponse.<String>builder()
                .result("Movie Genre relation has been deleted")
                .build();
    }
}

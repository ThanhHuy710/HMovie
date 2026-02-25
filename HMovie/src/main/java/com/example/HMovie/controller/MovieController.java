package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.AdvanceSearchRequest;
import com.example.HMovie.dto.request.MovieRequest;
import com.example.HMovie.dto.response.MovieResponse;
import com.example.HMovie.service.MovieService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MovieController {
    MovieService movieService;

    @GetMapping
    ApiResponse<List<MovieResponse>> getAllMovies() {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getAllMovies())
                .build();
    }

    @GetMapping("/title/{title}")
    ApiResponse<List<MovieResponse>> getMovieByTitle(@PathVariable("title") String title) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByTitle(title))
                .build();
    }
    @GetMapping("/criteria")
    ApiResponse<List<MovieResponse>> getMovieByCriteria(@ModelAttribute AdvanceSearchRequest request){
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByCriteria(request)).build();
    }
    @GetMapping("/is_series/{isSeries}")
    ApiResponse<List<MovieResponse>> getMovieBySeries(@PathVariable("isSeries") Boolean isSeries) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieBySeries(isSeries))
                .build();
    }

    @GetMapping("/directors/{director}")
    ApiResponse<List<String>> getDirectorsByDirectorName(@PathVariable("director") String director) {
        return ApiResponse.<List<String>>builder()
                .result(movieService.getDirectorsByDirectorName(director))
                .build();
    }

    @GetMapping("/director/{director}")
    ApiResponse<List<MovieResponse>> getMovieByDirector(@PathVariable("director") String director) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByDirector(director))
                .build();
    }

    @GetMapping("/actors/{actor}")
    ApiResponse<List<String>> getActorsMovieByActor(@PathVariable("actor") String actor) {
        return ApiResponse.<List<String>>builder()
                .result(movieService.getActorsMovieByActor(actor))
                .build();
    }

    @GetMapping("/actor/{actor}")
    ApiResponse<List<MovieResponse>> getMovieByActor(@PathVariable("actor") String actor) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByActor(actor))
                .build();
    }

    @GetMapping("/year/{year}")
    ApiResponse<List<MovieResponse>> getMovieByYear(@PathVariable("year") Integer year) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByYear(year))
                .build();
    }
    @GetMapping("/country/{country}")
    ApiResponse<List<MovieResponse>> getMovieByCountry(@PathVariable("country") String country) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByCountry(country))
                .build();
    }
    @GetMapping("/genre/{genre}")
    ApiResponse<List<MovieResponse>> getMovieByGenre(@PathVariable("genre") String genre) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByGenre(genre))
                .build();
    }
    @GetMapping("/favorite")
    ApiResponse<List<MovieResponse>> getMovieByFavorite() {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByFavorite())
                .build();
    }
    @GetMapping("/top-hot")
    ApiResponse<List<MovieResponse>> getMovieByViewCount() {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByViewCount())
                .build();
    }
    @GetMapping("/top-rating")
    ApiResponse<List<MovieResponse>> getMovieByAverageRating() {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovieByAverageRating())
                .build();
    }
    @GetMapping("/season/{movieId}")
    ApiResponse<List<MovieResponse>> getSeasonByMovieId(@PathVariable("movieId") String movieId) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getSeasonByMovieId(movieId))
                .build();
    }
    @GetMapping("/favorites/{profileId}")
    ApiResponse<List<MovieResponse>> getFavoritesMovieByProfileId(@PathVariable("profileId") String profileId) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getFavoritesMovieByProfileId(profileId))
                .build();
    }
    @GetMapping("/histories/{profileId}")
    ApiResponse<List<MovieResponse>> getHistoryMovieByProfileId(@PathVariable("profileId") String profileId) {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getHistoryMovieByProfileId(profileId))
                .build();
    }
    @GetMapping("/{movieId}")
    ApiResponse<MovieResponse> getMovieById(@PathVariable("movieId") String movieId) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.getMovieById(movieId))
                .build();
    }

    @PostMapping
    ApiResponse<MovieResponse> createMovie(@RequestBody @Valid MovieRequest request) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.createMovie(request))
                .build();
    }

    @PutMapping("/{movieId}")
    ApiResponse<MovieResponse> updateMovie(@PathVariable("movieId") String movieId, @RequestBody @Valid MovieRequest request) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.updateMovie(movieId, request))
                .build();
    }

    @DeleteMapping("/{movieId}")
    ApiResponse<String> deleteMovie(@PathVariable("movieId") String movieId) {
        movieService.deleteMovie(movieId);
        return ApiResponse.<String>builder()
                .result("Movie has been deleted")
                .build();
    }
}

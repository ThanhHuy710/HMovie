package com.example.HMovie.service;

import com.example.HMovie.dto.request.AdvanceSearchRequest;
import com.example.HMovie.dto.request.MovieRequest;
import com.example.HMovie.dto.response.MovieResponse;
import com.example.HMovie.entity.Movie;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.MovieMapper;
import com.example.HMovie.repository.MovieRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MovieService {
    MovieRepository movieRepository;
    MovieMapper movieMapper;

    @Cacheable(value = "movie_list")
    public List<MovieResponse> getAllMovies() {
        log.info("getAllMovies: Fetching from Database");
        return movieRepository.findAll().stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_detail", key = "#movieId")
    public MovieResponse getMovieById(String movieId) {
        log.info("getMovieById: Fetching from Database for id {}", movieId);
        return movieMapper.toMovieResponse(movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND)));
    }

    @Cacheable(value = "movie_search", key = "'title_' + #title")
    public List<MovieResponse> getMovieByTitle(String title) {
        log.info("getMovieByTitle: Fetching from Database for title {}", title);
        return movieRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'series_' + #isSeries")
    public List<MovieResponse> getMovieBySeries(Boolean isSeries) {
        log.info("getMovieBySeries: Fetching from Database for isSeries {}", isSeries);
        return movieRepository.findByIsSeries(isSeries).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(evict = {
            @CacheEvict(value = "movie_list", allEntries = true),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public MovieResponse createMovie(MovieRequest request) {
        log.info("createMovie: Creating new movie");
        Movie movie = movieMapper.toMovie(request);
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(evict = {
            @CacheEvict(value = "movie_list", allEntries = true),
            @CacheEvict(value = "movie_detail", key = "#movieId"),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public MovieResponse updateMovie(String movieId, MovieRequest request) {
        log.info("updateMovie: Updating movie {}", movieId);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        movieMapper.updateMovie(movie, request);

        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(evict = {
            @CacheEvict(value = "movie_list", allEntries = true),
            @CacheEvict(value = "movie_detail", key = "#movieId"),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public void deleteMovie(String movieId) {
        log.info("deleteMovie: Deleting movie {}", movieId);
        if (!movieRepository.existsById(movieId)) {
            throw new AppException(ErrorCode.MOVIE_NOT_FOUND);
        }
        movieRepository.deleteById(movieId);
    }

    @Cacheable(value = "movie_search", key = "'director_names_' + #director")
    public List<String> getDirectorsByDirectorName(String director) {
        log.info("getDirectorsByDirectorName: Fetching directors for name {}", director);
        List<String> directorStrings = movieRepository.findDirectorStringsByDirector(director);

        return directorStrings.stream()
                .flatMap(dirStr -> Arrays.stream(dirStr.split(",")))
                .map(String::trim)
                .filter(d -> d.toLowerCase().contains(director.toLowerCase()))
                .distinct()
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'director_movies_' + #director")
    public List<MovieResponse> getMovieByDirector(String director) {
        log.info("getMovieByDirector: Fetching movies for director {}", director);
        return movieRepository.findByDirectorContainingIgnoreCase(director).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'actor_names_' + #actor")
    public List<String> getActorsMovieByActor(String actor) {
        List<String> actorStrings = movieRepository.findActorStringsByActor(actor);

        return actorStrings.stream()
                .flatMap(actorStr -> Arrays.stream(actorStr.split(",")))
                .map(String::trim)
                .filter(a -> a.toLowerCase().contains(actor.toLowerCase()))
                .distinct()
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'actor_movies_' + #actor")
    public List<MovieResponse> getMovieByActor(String actor) {
        log.info("getMovieByActor: Fetching movies for actor {}", actor);
        return movieRepository.findByActorContainingIgnoreCase(actor).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'year_' + #year")
    public List<MovieResponse> getMovieByYear(Integer year) {
        log.info("getMovieByYear: Fetching from Database for year {}", year);
        return movieRepository.findByYear(year).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'country_' + #country")
    public List<MovieResponse> getMovieByCountry(String country) {
        log.info("getMovieByCountry: Fetching from Database for country {}", country);
        return movieRepository.findByCountry(country).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'top_favorite'")
    public List<MovieResponse> getMovieByFavorite() {
        return movieRepository.findAllByOrderByFavoriteCountDesc().stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'top_view'")
    public List<MovieResponse> getMovieByViewCount() {
        return movieRepository.findAllByOrderByViewCountDesc().stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'top_rating'")
    public List<MovieResponse> getMovieByAverageRating() {
        return movieRepository.findAllByOrderByAverageRatingDesc().stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'genre_' + #genre")
    public List<MovieResponse> getMovieByGenre(String genre) {
        return movieRepository.findMoviesByGenreName(genre).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @Cacheable(value = "movie_search", key = "'season_' + #movieId")
    public List<MovieResponse> getSeasonByMovieId(String movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        return movieRepository.findByOriginalNameOrderBySeasonAsc(movie.getOriginalName()).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    public List<MovieResponse> getFavoritesMovieByProfileId(String profileId) {
        return movieRepository.findFavoriteMoviesByProfileId(profileId).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    public List<MovieResponse> getHistoryMovieByProfileId(String profileId) {
        return movieRepository.findHistoryMoviesByProfileId(profileId).stream()
                .map(movieMapper::toMovieResponse)
                .toList();
    }

    public List<MovieResponse> getMovieByCriteria(@Valid AdvanceSearchRequest request) {
            String country = request.getCountry();
            List<String> genreList = null;
            if (StringUtils.hasText(request.getGenres())) {
                genreList = Arrays.stream(request.getGenres().split(","))
                        .map(String::trim)
                        .toList();
                if (genreList.isEmpty()) {
                    genreList = null;
                }
            }

            String ageRating = StringUtils.hasText(request.getAgeRating()) ? request.getAgeRating().trim() : null;

            List<Movie> movies = movieRepository.findMoviesByCriteria(
                    country,
                    request.getIsSeries(),
                    request.getYear(),
                    ageRating,
                    genreList
            );

            return movies.stream()
                    .map(movieMapper::toMovieResponse)
                    .toList();
        }
}

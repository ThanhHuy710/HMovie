package com.example.HMovie.service;

import com.example.HMovie.dto.request.MovieGenreRequest;
import com.example.HMovie.dto.response.MovieGenreResponse;
import com.example.HMovie.entity.MovieGenre;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.MovieGenreMapper;
import com.example.HMovie.repository.MovieGenreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MovieGenreService {
    MovieGenreRepository movieGenreRepository;
    MovieGenreMapper movieGenreMapper;

    @Cacheable(value = "movie_genre_list")
    public List<MovieGenreResponse> getAllMovieGenres() {
        log.info("getAllMovieGenres: Fetching from Database");
        return movieGenreRepository.findAll().stream()
                .map(movieGenreMapper::toMovieGenreResponse)
                .toList();
    }

    @Cacheable(value = "movie_genre_detail", key = "#movieGenreId")
    public MovieGenreResponse getMovieGenreById(String movieGenreId) {
        log.info("getMovieGenreById: Fetching from Database for id {}", movieGenreId);
        return movieGenreMapper.toMovieGenreResponse(movieGenreRepository.findById(movieGenreId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_GENRE_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(evict = {
            @CacheEvict(value = "movie_genre_list", allEntries = true),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public MovieGenreResponse createMovieGenre(MovieGenreRequest request) {
        log.info("createMovieGenre: Creating new movie genre relation");
        MovieGenre movieGenre = movieGenreMapper.toMovieGenre(request);
        return movieGenreMapper.toMovieGenreResponse(movieGenreRepository.save(movieGenre));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(evict = {
            @CacheEvict(value = "movie_genre_list", allEntries = true),
            @CacheEvict(value = "movie_genre_detail", key = "#movieGenreId"),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public MovieGenreResponse updateMovieGenre(String movieGenreId, MovieGenreRequest request) {
        log.info("updateMovieGenre: Updating movie genre relation {}", movieGenreId);
        MovieGenre movieGenre = movieGenreRepository.findById(movieGenreId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_GENRE_NOT_FOUND));
        
        movieGenreMapper.updateMovieGenre(movieGenre, request);
        return movieGenreMapper.toMovieGenreResponse(movieGenreRepository.save(movieGenre));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(evict = {
            @CacheEvict(value = "movie_genre_list", allEntries = true),
            @CacheEvict(value = "movie_genre_detail", key = "#movieGenreId"),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public void deleteMovieGenre(String movieGenreId) {
        log.info("deleteMovieGenre: Deleting movie genre relation {}", movieGenreId);
        if (!movieGenreRepository.existsById(movieGenreId)) {
            throw new AppException(ErrorCode.MOVIE_GENRE_NOT_FOUND);
        }
        movieGenreRepository.deleteById(movieGenreId);
    }

    @Caching(evict = {
            @CacheEvict(value = "movie_genre_list", allEntries = true),
            @CacheEvict(value = "movie_genre_detail", allEntries = true),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public void deleteMovieGenreByMovieId(String movieId) {
        log.info("deleteMovieGenreByMovieId: Deleting movie genre relation {}", movieId);
        if(!movieGenreRepository.existsByMovie_MovieId(movieId))
        {
            throw new AppException(ErrorCode.MOVIE_GENRE_NOT_FOUND);
        }
        movieGenreRepository.deleteAllByMovie_MovieId(movieId);
    }
}

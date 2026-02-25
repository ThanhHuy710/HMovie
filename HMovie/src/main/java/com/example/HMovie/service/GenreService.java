package com.example.HMovie.service;

import com.example.HMovie.dto.request.GenreRequest;
import com.example.HMovie.dto.response.GenreResponse;
import com.example.HMovie.entity.Genre;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.GenreMapper;
import com.example.HMovie.repository.GenreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GenreService {
    GenreRepository genreRepository;
    GenreMapper genreMapper;

    @Cacheable(value = "GenreRepository", key = "'allGenres'")
    public List<GenreResponse> getAllGenres() {
        log.info("getAllGenres: Fetching from Database");
        return genreRepository.findAll().stream()
                .map(genreMapper::toGenreResponse)
                .toList();
    }

    @Cacheable(value = "GenreRepository", key = "#genreId")
    public GenreResponse getGenreById(String genreId) {
        log.info("getGenreById: Fetching from Database for id {}", genreId);
        return genreMapper.toGenreResponse(genreRepository.findById(genreId)
                .orElseThrow(() -> new AppException(ErrorCode.GENRE_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "GenreRepository", key = "'allGenres'")
    public GenreResponse createGenre(GenreRequest request) {
        log.info("createGenre: Creating new genre");
        Genre genre = genreMapper.toGenre(request);
        return genreMapper.toGenreResponse(genreRepository.save(genre));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "GenreRepository", allEntries = true)
    public GenreResponse updateGenre(String genreId, GenreRequest request) {
        log.info("updateGenre: Updating genre {}", genreId);
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new AppException(ErrorCode.GENRE_NOT_FOUND));
        
        genreMapper.updateGenre(genre, request);
        return genreMapper.toGenreResponse(genreRepository.save(genre));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "GenreRepository", allEntries = true)
    public void deleteGenre(String genreId) {
        log.info("deleteGenre: Deleting genre {}", genreId);
        if (!genreRepository.existsById(genreId)) {
            throw new AppException(ErrorCode.GENRE_NOT_FOUND);
        }
        genreRepository.deleteById(genreId);
    }
}

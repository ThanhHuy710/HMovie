package com.example.HMovie.service;

import com.example.HMovie.dto.request.FavoriteRequest;
import com.example.HMovie.dto.response.FavoriteResponse;
import com.example.HMovie.entity.Favorite;
import com.example.HMovie.entity.Movie;
import com.example.HMovie.entity.Profile;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.FavoriteMapper;
import com.example.HMovie.repository.FavoriteRepository;
import com.example.HMovie.repository.MovieRepository;
import com.example.HMovie.repository.ProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FavoriteService {
    FavoriteRepository favoriteRepository;
    FavoriteMapper favoriteMapper;
    MovieRepository movieRepository;
    ProfileRepository profileRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Cacheable(value = "FavoriteRepository", key = "'allFavorites'")
    public List<FavoriteResponse> getAllFavorites() {
        log.info("getAllFavorites: Fetching from Database");
        return favoriteRepository.findAll().stream()
                .map(favoriteMapper::toFavoriteResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "FavoriteRepository", key = "#favoriteId")
    public FavoriteResponse getFavoriteById(String favoriteId) {
        log.info("getFavoriteById: Fetching from Database for id {}", favoriteId);
        return favoriteMapper.toFavoriteResponse(favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new AppException(ErrorCode.FAVORITE_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('USER')")
    @Caching(evict = {
            @CacheEvict(value = "FavoriteRepository", key = "'allFavorites'"),
            @CacheEvict(value = "MovieRepository", allEntries = true) // Xóa cache Movie để cập nhật Rating mới
    })
    @Transactional
    public FavoriteResponse createFavorite(FavoriteRequest request) {
        log.info("createFavorite: Creating new favorite");

        Favorite favorite = favoriteMapper.toFavorite(request);

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        favorite.setMovie(movie);

        Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new AppException(ErrorCode.PROFILE_NOT_FOUND));
        favorite.setProfile(profile);

        favorite = favoriteRepository.save(favorite);

        favoriteRepository.calculateTotalFavoriteCountByMovieId(request.getMovieId());

        return favoriteMapper.toFavoriteResponse(favorite);
    }

    @PreAuthorize("hasRole('USER')")
    @CacheEvict(value = "FavoriteRepository", allEntries = true)
    public FavoriteResponse updateFavorite(String favoriteId, FavoriteRequest request) {
        log.info("updateFavorite: Updating favorite {}", favoriteId);
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new AppException(ErrorCode.FAVORITE_NOT_FOUND));
        
        favoriteMapper.updateFavorite(favorite, request);

        if (request.getMovieId() != null) {
             Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
             favorite.setMovie(movie);
        }
        if (request.getProfileId() != null) {
            Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));
            favorite.setProfile(profile);
        }

        return favoriteMapper.toFavoriteResponse(favoriteRepository.save(favorite));
    }

    @PreAuthorize("hasRole('USER')")
    @CacheEvict(value = "FavoriteRepository", allEntries = true)
    public void deleteFavorite(String favoriteId) {
        log.info("deleteFavorite: Deleting favorite {}", favoriteId);
        if (!favoriteRepository.existsById(favoriteId)) {
            throw new AppException(ErrorCode.FAVORITE_NOT_FOUND);
        }
        favoriteRepository.deleteById(favoriteId);
    }

    @PreAuthorize("hasRole('USER')")
    @CacheEvict(value = "FavoriteRepository", allEntries = true)
    public List<FavoriteResponse> getFavoritesByProfileId(String profile) {
        return favoriteRepository.findByProfileProfileId(profile).stream()
                .map(favoriteMapper::toFavoriteResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    @CacheEvict(value = "FavoriteRepository", allEntries = true)
    public FavoriteResponse getFavoriteByProfileIdAndMovieId(String profileId, String movieId) {
        return favoriteMapper.toFavoriteResponse(favoriteRepository.findByMovieMovieIdAndProfileProfileId(movieId, profileId)
                .orElseThrow(() -> new AppException(ErrorCode.FAVORITE_NOT_FOUND)));
    }
}

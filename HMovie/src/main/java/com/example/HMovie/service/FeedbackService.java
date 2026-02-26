package com.example.HMovie.service;

import com.example.HMovie.dto.request.FeedbackRequest;
import com.example.HMovie.dto.response.FeedbackResponse;
import com.example.HMovie.entity.Feedback;
import com.example.HMovie.entity.Movie;
import com.example.HMovie.entity.Profile;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.FeedbackMapper;
import com.example.HMovie.repository.FeedbackRepository;
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
public class FeedbackService {
    FeedbackRepository feedbackRepository;
    FeedbackMapper feedbackMapper;
    MovieRepository movieRepository;
    ProfileRepository profileRepository;

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "feedback_list")
    public List<FeedbackResponse> getAllFeedbacks() {
        log.info("getAllFeedbacks: Fetching from Database");
        return feedbackRepository.findAll().stream()
                .map(feedbackMapper::toFeedbackResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "feedback_detail", key = "#feedbackId")
    public FeedbackResponse getFeedbackById(String feedbackId) {
        log.info("getFeedbackById: Fetching from Database for id {}", feedbackId);
        return feedbackMapper.toFeedbackResponse(feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new AppException(ErrorCode.FEEDBACK_NOT_FOUND)));
    }

    @Cacheable(value = "feedback_list_by_movie", key = "#movieId")
    public List<FeedbackResponse> getFeedbacksByMovieId(String movieId) {
        log.info("getFeedbacksByMovieId: Fetching feedbacks for movie {}", movieId);
        return feedbackRepository.findByMovieMovieId(movieId).stream()
                .map(feedbackMapper::toFeedbackResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "feedback_list", allEntries = true),
            @CacheEvict(value = "feedback_list_by_movie", allEntries = true),
            @CacheEvict(value = "movie_list", allEntries = true),
            @CacheEvict(value = "movie_detail", allEntries = true),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public FeedbackResponse createFeedback(FeedbackRequest request) {
        log.info("createFeedback: Creating new feedback");
        
        Feedback feedback = feedbackMapper.toFeedback(request);
        
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        feedback.setMovie(movie);

        Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        feedback.setProfile(profile);

        // Dùng saveAndFlush để đảm bảo tính toán đúng
        feedback = feedbackRepository.saveAndFlush(feedback);
        
        movieRepository.calculateAverageRatingByMovieId(request.getMovieId());

        return feedbackMapper.toFeedbackResponse(feedback);
    }

    @PreAuthorize("hasRole('USER')")
    @Caching(evict = {
            @CacheEvict(value = "feedback_list", allEntries = true),
            @CacheEvict(value = "feedback_detail", key = "#feedbackId"),
            @CacheEvict(value = "feedback_list_by_movie", allEntries = true),
            @CacheEvict(value = "movie_list", allEntries = true),
            @CacheEvict(value = "movie_detail", allEntries = true),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public FeedbackResponse updateFeedback(String feedbackId, FeedbackRequest request) {
        log.info("updateFeedback: Updating feedback {}", feedbackId);
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new AppException(ErrorCode.FEEDBACK_NOT_FOUND));
        
        feedbackMapper.updateFeedback(feedback, request);
        
        if (request.getMovieId() != null) {
             Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
             feedback.setMovie(movie);
        }
        if (request.getProfileId() != null) {
            Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));
            feedback.setProfile(profile);
        }
        
        feedback = feedbackRepository.saveAndFlush(feedback);
        
        if (request.getRating() != null || request.getMovieId() != null) {
             movieRepository.calculateAverageRatingByMovieId(feedback.getMovie().getMovieId());
        }

        return feedbackMapper.toFeedbackResponse(feedback);
    }

    @PreAuthorize("hasRole('USER')")
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "feedback_list", allEntries = true),
            @CacheEvict(value = "feedback_detail", key = "#feedbackId"),
            @CacheEvict(value = "feedback_list_by_movie", allEntries = true),
            @CacheEvict(value = "movie_list", allEntries = true),
            @CacheEvict(value = "movie_detail", allEntries = true),
            @CacheEvict(value = "movie_search", allEntries = true)
    })
    public void deleteFeedback(String feedbackId) {
        log.info("deleteFeedback: Deleting feedback {}", feedbackId);
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new AppException(ErrorCode.FEEDBACK_NOT_FOUND));
        
        String movieId = feedback.getMovie().getMovieId();
        
        feedbackRepository.delete(feedback);
        feedbackRepository.flush();
        
        movieRepository.calculateAverageRatingByMovieId(movieId);
    }
}

package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.FeedbackRequest;
import com.example.HMovie.dto.response.FeedbackResponse;
import com.example.HMovie.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FeedbackController {
    FeedbackService feedbackService;

    @GetMapping
    ApiResponse<List<FeedbackResponse>> getAllFeedbacks() {
        return ApiResponse.<List<FeedbackResponse>>builder()
                .result(feedbackService.getAllFeedbacks())
                .build();
    }

    @GetMapping("/{feedbackId}")
    ApiResponse<FeedbackResponse> getFeedbackById(@PathVariable("feedbackId") String feedbackId) {
        return ApiResponse.<FeedbackResponse>builder()
                .result(feedbackService.getFeedbackById(feedbackId))
                .build();
    }
    
    // Thêm API lấy feedback theo phim
    @GetMapping("/movie/{movieId}")
    ApiResponse<List<FeedbackResponse>> getFeedbacksByMovieId(@PathVariable("movieId") String movieId) {
        return ApiResponse.<List<FeedbackResponse>>builder()
                .result(feedbackService.getFeedbacksByMovieId(movieId))
                .build();
    }

    @PostMapping
    ApiResponse<FeedbackResponse> createFeedback(@RequestBody @Valid FeedbackRequest request) {
        return ApiResponse.<FeedbackResponse>builder()
                .result(feedbackService.createFeedback(request))
                .build();
    }

    @PutMapping("/{feedbackId}")
    ApiResponse<FeedbackResponse> updateFeedback(@PathVariable("feedbackId") String feedbackId, @RequestBody @Valid FeedbackRequest request) {
        return ApiResponse.<FeedbackResponse>builder()
                .result(feedbackService.updateFeedback(feedbackId, request))
                .build();
    }

    @DeleteMapping("/{feedbackId}")
    ApiResponse<String> deleteFeedback(@PathVariable("feedbackId") String feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ApiResponse.<String>builder()
                .result("Feedback has been deleted")
                .build();
    }
}

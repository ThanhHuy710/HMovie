package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.EpisodeRequest;
import com.example.HMovie.dto.response.EpisodeResponse;
import com.example.HMovie.service.EpisodeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/episodes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EpisodeController {
    EpisodeService episodeService;

    @GetMapping
    ApiResponse<List<EpisodeResponse>> getAllEpisodes() {
        return ApiResponse.<List<EpisodeResponse>>builder()
                .result(episodeService.getAllEpisodes())
                .build();
    }

    @GetMapping("/{episodeId}")
    ApiResponse<EpisodeResponse> getEpisodeById(@PathVariable("episodeId") String episodeId) {
        return ApiResponse.<EpisodeResponse>builder()
                .result(episodeService.getEpisodeById(episodeId))
                .build();
    }

    @PostMapping
    ApiResponse<EpisodeResponse> createEpisode(@RequestBody @Valid EpisodeRequest request) {
        return ApiResponse.<EpisodeResponse>builder()
                .result(episodeService.createEpisode(request))
                .build();
    }

    @PutMapping("/{episodeId}")
    ApiResponse<EpisodeResponse> updateEpisode(@PathVariable("episodeId") String episodeId, @RequestBody @Valid EpisodeRequest request) {
        return ApiResponse.<EpisodeResponse>builder()
                .result(episodeService.updateEpisode(episodeId, request))
                .build();
    }

    @DeleteMapping("/{episodeId}")
    ApiResponse<String> deleteEpisode(@PathVariable("episodeId") String episodeId) {
        episodeService.deleteEpisode(episodeId);
        return ApiResponse.<String>builder()
                .result("Episode has been deleted")
                .build();
    }
}

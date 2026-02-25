package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.ViewRequest;
import com.example.HMovie.dto.response.ViewResponse;
import com.example.HMovie.service.ViewService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/views")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ViewController {
    ViewService viewService;

    @GetMapping
    ApiResponse<List<ViewResponse>> getAllViews() {
        return ApiResponse.<List<ViewResponse>>builder()
                .result(viewService.getAllViews())
                .build();
    }
    @GetMapping("/profile/{profileId}")
    ApiResponse<List<ViewResponse>> getViewsByProfileId(@PathVariable("profileId") String profile) {
        return ApiResponse.<List<ViewResponse>>builder()
                .result(viewService.getViewsByProfileId(profile))
                .build();
    }

    @GetMapping("/{viewId}")
    ApiResponse<ViewResponse> getViewById(@PathVariable("viewId") String viewId) {
        return ApiResponse.<ViewResponse>builder()
                .result(viewService.getViewById(viewId))
                .build();
    }

    @PostMapping
    ApiResponse<ViewResponse> createView(@RequestBody @Valid ViewRequest request) {
        return ApiResponse.<ViewResponse>builder()
                .result(viewService.createView(request))
                .build();
    }

    @PutMapping("/{viewId}")
    ApiResponse<ViewResponse> updateView(@PathVariable("viewId") String viewId, @RequestBody @Valid ViewRequest request) {
        return ApiResponse.<ViewResponse>builder()
                .result(viewService.updateView(viewId, request))
                .build();
    }

    @DeleteMapping("/{viewId}")
    ApiResponse<String> deleteView(@PathVariable("viewId") String viewId) {
        viewService.deleteView(viewId);
        return ApiResponse.<String>builder()
                .result("View has been deleted")
                .build();
    }
}

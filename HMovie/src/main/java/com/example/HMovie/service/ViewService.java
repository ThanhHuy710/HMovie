package com.example.HMovie.service;

import com.example.HMovie.dto.request.ViewRequest;
import com.example.HMovie.dto.response.ViewResponse;
import com.example.HMovie.entity.View;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.ViewMapper;
import com.example.HMovie.repository.ViewRepository;
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
public class ViewService {
    ViewRepository viewRepository;
    ViewMapper viewMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Cacheable(value = "view_list")
    public List<ViewResponse> getAllViews() {
        log.info("getAllViews: Fetching from Database");
        return viewRepository.findAll().stream()
                .map(viewMapper::toViewResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "view_detail", key = "#viewId")
    public ViewResponse getViewById(String viewId) {
        log.info("getViewById: Fetching from Database for id {}", viewId);
        return viewMapper.toViewResponse(viewRepository.findById(viewId)
                .orElseThrow(() -> new AppException(ErrorCode.VIEW_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('USER')")
    @Caching(evict = {
            @CacheEvict(value = "view_list", allEntries = true),
            @CacheEvict(value = "movie_list", allEntries = true),
            @CacheEvict(value = "movie_detail", allEntries = true)
    })
    public ViewResponse createView(ViewRequest request) {
        log.info("createView: Creating new view");
        View view = viewMapper.toView(request);
        viewRepository.calculateTotalViewCountByMovieId(request.getMovieId());
        return viewMapper.toViewResponse(viewRepository.save(view));
    }

    @PreAuthorize("hasRole('USER')")
    @Caching(
            evict = {
                    @CacheEvict(value = "view_list", allEntries = true),
                    @CacheEvict(value = "view_detail", key = "#viewId")
            }
    )
    public ViewResponse updateView(String viewId, ViewRequest request) {
        log.info("updateView: Updating view {}", viewId);
        View view = viewRepository.findById(viewId)
                .orElseThrow(() -> new AppException(ErrorCode.VIEW_NOT_FOUND));
        
        viewMapper.updateView(view, request);
        return viewMapper.toViewResponse(viewRepository.save(view));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(
            evict = {
                    @CacheEvict(value = "view_list", allEntries = true),
                    @CacheEvict(value = "view_detail", key = "#viewId")
            }
    )
    public void deleteView(String viewId) {
        log.info("deleteView: Deleting view {}", viewId);
        if (!viewRepository.existsById(viewId)) {
            throw new AppException(ErrorCode.VIEW_NOT_FOUND);
        }
        viewRepository.deleteById(viewId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Cacheable(value = "view_list_by_profile", key="#profile")
    public List<ViewResponse> getViewsByProfileId(String profile) {
        return viewRepository.findByProfileProfileId(profile).stream()
                .map(viewMapper::toViewResponse)
                .toList();
    }
}

package com.example.HMovie.service;

import com.example.HMovie.dto.request.EpisodeRequest;
import com.example.HMovie.dto.response.EpisodeResponse;
import com.example.HMovie.entity.Episode;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.EpisodeMapper;
import com.example.HMovie.repository.EpisodeRepository;
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
public class EpisodeService {
    EpisodeRepository episodeRepository;
    EpisodeMapper episodeMapper;

    @Cacheable(value = "EpisodeRepository", key = "'allEpisodes'")
    public List<EpisodeResponse> getAllEpisodes() {
        log.info("getAllEpisodes: Fetching from Database");
        return episodeRepository.findAll().stream()
                .map(episodeMapper::toEpisodeResponse)
                .toList();
    }

    @Cacheable(value = "EpisodeRepository", key = "#episodeId")
    public EpisodeResponse getEpisodeById(String episodeId) {
        log.info("getEpisodeById: Fetching from Database for id {}", episodeId);
        return episodeMapper.toEpisodeResponse(episodeRepository.findById(episodeId)
                .orElseThrow(() -> new AppException(ErrorCode.EPISODE_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "EpisodeRepository", key = "'allEpisodes'")
    public EpisodeResponse createEpisode(EpisodeRequest request) {
        log.info("createEpisode: Creating new episode");
        Episode episode = episodeMapper.toEpisode(request);
        return episodeMapper.toEpisodeResponse(episodeRepository.save(episode));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "EpisodeRepository", allEntries = true)
    public EpisodeResponse updateEpisode(String episodeId, EpisodeRequest request) {
        log.info("updateEpisode: Updating episode {}", episodeId);
        Episode episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> new AppException(ErrorCode.EPISODE_NOT_FOUND));
        
        episodeMapper.updateEpisode(episode, request);
        return episodeMapper.toEpisodeResponse(episodeRepository.save(episode));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "EpisodeRepository", allEntries = true)
    public void deleteEpisode(String episodeId) {
        log.info("deleteEpisode: Deleting episode {}", episodeId);
        if (!episodeRepository.existsById(episodeId)) {
            throw new AppException(ErrorCode.EPISODE_NOT_FOUND);
        }
        episodeRepository.deleteById(episodeId);
    }
}

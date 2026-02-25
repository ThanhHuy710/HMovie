package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.EpisodeRequest;
import com.example.HMovie.dto.response.EpisodeResponse;
import com.example.HMovie.entity.Episode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EpisodeMapper {
    @Mapping(target = "movie.movieId", source = "movieId")
    Episode toEpisode(EpisodeRequest request);

    @Mapping(target = "movieId", source = "movie.movieId")
    EpisodeResponse toEpisodeResponse(Episode episode);

    @Mapping(target = "movie.movieId", source = "movieId")
    void updateEpisode(@MappingTarget Episode episode, EpisodeRequest request);
}

package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.MovieRequest;
import com.example.HMovie.dto.response.EpisodeResponse;
import com.example.HMovie.dto.response.MovieResponse;
import com.example.HMovie.entity.Episode;
import com.example.HMovie.entity.Movie;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    Movie toMovie(MovieRequest request);
    MovieResponse toMovieResponse(Movie movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovie(@MappingTarget Movie movie, MovieRequest request);
    EpisodeResponse toEpisodeResponse(Episode episode);
}

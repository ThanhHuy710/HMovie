package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.MovieGenreRequest;
import com.example.HMovie.dto.response.MovieGenreResponse;
import com.example.HMovie.entity.MovieGenre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieGenreMapper {
    @Mapping(target = "genre.genreId", source = "genreId")
    @Mapping(target = "movie.movieId", source = "movieId")
    MovieGenre toMovieGenre(MovieGenreRequest request);

    @Mapping(target = "genreId", source = "genre.genreId")
    @Mapping(target = "movieId", source = "movie.movieId")
    MovieGenreResponse toMovieGenreResponse(MovieGenre movieGenre);

    @Mapping(target = "genre.genreId", source = "genreId")
    @Mapping(target = "movie.movieId", source = "movieId")
    void updateMovieGenre(@MappingTarget MovieGenre movieGenre, MovieGenreRequest request);
}

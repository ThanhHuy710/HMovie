package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.GenreRequest;
import com.example.HMovie.dto.response.GenreResponse;
import com.example.HMovie.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenreMapper {
    Genre toGenre(GenreRequest request);
    GenreResponse toGenreResponse(Genre genre);
    void updateGenre(@MappingTarget Genre genre, GenreRequest request);
}

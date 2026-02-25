package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.FavoriteRequest;
import com.example.HMovie.dto.response.FavoriteResponse;
import com.example.HMovie.entity.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FavoriteMapper {
    @Mapping(target = "profile.profileId", source = "profileId")
    @Mapping(target = "movie.movieId", source = "movieId")
    Favorite toFavorite(FavoriteRequest request);

    @Mapping(target = "profileId", source = "profile.profileId")
    @Mapping(target = "movieId", source = "movie.movieId")
    FavoriteResponse toFavoriteResponse(Favorite favorite);

    @Mapping(target = "profile.profileId", source = "profileId")
    @Mapping(target = "movie.movieId", source = "movieId")
    void updateFavorite(@MappingTarget Favorite favorite, FavoriteRequest request);
}

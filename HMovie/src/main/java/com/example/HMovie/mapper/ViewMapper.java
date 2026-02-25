package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.ViewRequest;
import com.example.HMovie.dto.response.ViewResponse;
import com.example.HMovie.entity.View;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ViewMapper {
    @Mapping(target = "movie.movieId", source = "movieId")
    @Mapping(target = "profile.profileId", source = "profileId")
    View toView(ViewRequest request);

    @Mapping(target = "movieId", source = "movie.movieId")
    @Mapping(target = "profileId", source = "profile.profileId")
    ViewResponse toViewResponse(View view);

    @Mapping(target = "movie.movieId", source = "movieId")
    @Mapping(target = "profile.profileId", source = "profileId")
    void updateView(@MappingTarget View view, ViewRequest request);
}

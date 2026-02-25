package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.FeedbackRequest;
import com.example.HMovie.dto.response.FeedbackResponse;
import com.example.HMovie.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackMapper {
    @Mapping(target = "profile.profileId", source = "profileId")
    @Mapping(target = "movie.movieId", source = "movieId")
    Feedback toFeedback(FeedbackRequest request);

    @Mapping(target = "profileId", source = "profile.profileId")
    @Mapping(target = "movieId", source = "movie.movieId")
    FeedbackResponse toFeedbackResponse(Feedback feedback);

    @Mapping(target = "profile.profileId", source = "profileId")
    @Mapping(target = "movie.movieId", source = "movieId")
    void updateFeedback(@MappingTarget Feedback feedback, FeedbackRequest request);
}

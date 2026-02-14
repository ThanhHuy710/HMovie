package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.RegistrationRequest;
import com.example.HMovie.dto.response.ProfileResponse;
import com.example.HMovie.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(RegistrationRequest request);

    ProfileResponse toProfileResponse(Profile profile);
}

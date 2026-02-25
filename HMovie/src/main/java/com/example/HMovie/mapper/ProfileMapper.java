package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.ProfileRequest;
import com.example.HMovie.dto.request.RegistrationRequest;
import com.example.HMovie.dto.response.ProfileResponse;
import com.example.HMovie.entity.Profile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {
    Profile toProfile(RegistrationRequest request);
    ProfileResponse toProfileResponse(Profile profile);
    
    // Cấu hình bỏ qua các trường null khi update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfile(@MappingTarget Profile profile, ProfileRequest request);
}

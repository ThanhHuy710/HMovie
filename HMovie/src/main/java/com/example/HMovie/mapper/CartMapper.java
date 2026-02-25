package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.CartRequest;
import com.example.HMovie.dto.response.CartResponse;
import com.example.HMovie.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper {
    @Mapping(target = "profile.profileId", source = "profileId")
    @Mapping(target = "plan.planId", source = "planId")
    Cart toCart(CartRequest request);

    @Mapping(target = "profileId", source = "profile.profileId")
    @Mapping(target = "planId", source = "plan.planId")
    CartResponse toCartResponse(Cart cart);

    @Mapping(target = "profile.profileId", source = "profileId")
    @Mapping(target = "plan.planId", source = "planId")
    void updateCart(@MappingTarget Cart cart, CartRequest request);
}

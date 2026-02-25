package com.example.HMovie.mapper;

import com.example.HMovie.dto.request.InvoiceRequest;
import com.example.HMovie.dto.response.InvoiceResponse;
import com.example.HMovie.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper {
    @Mapping(target = "profile.profileId", source = "profileId")
    @Mapping(target = "plan.planId", source = "planId")
    Invoice toInvoice(InvoiceRequest request);

    @Mapping(target = "profileId", source = "profile.profileId")
    @Mapping(target = "planId", source = "plan.planId")
    InvoiceResponse toInvoiceResponse(Invoice invoice);

    @Mapping(target = "profile.profileId", source = "profileId")
    @Mapping(target = "plan.planId", source = "planId")
    void updateInvoice(@MappingTarget Invoice invoice, InvoiceRequest request);
}

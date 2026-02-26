package com.example.HMovie.service;

import com.example.HMovie.dto.request.PaymentRequest;
import com.example.HMovie.dto.response.InvoiceResponse;
import com.example.HMovie.entity.Invoice;
import com.example.HMovie.entity.Plan;
import com.example.HMovie.entity.Profile;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.InvoiceMapper;
import com.example.HMovie.repository.InvoiceRepository;
import com.example.HMovie.repository.PlanRepository;
import com.example.HMovie.repository.ProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentService {
    InvoiceRepository invoiceRepository;
    ProfileRepository profileRepository;
    PlanRepository planRepository;
    InvoiceMapper invoiceMapper;

    @Transactional
    @PreAuthorize("hasRole('USER')")
    @Caching(
            evict = {
                    @CacheEvict(value = "InvoiceRepository", allEntries = true),
                    @CacheEvict(value = "ProfileRepository", allEntries = true)
            }
    )
    public InvoiceResponse processPayment(PaymentRequest request) {
        //kiểm tra null
        if (!StringUtils.hasText(request.getProfileId())) {
            log.warn("processPayment called with empty profileId");
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND);
        }
        if (!StringUtils.hasText(request.getPlanId())) {
            log.warn("processPayment called with empty planId");
            throw new AppException(ErrorCode.PLAN_NOT_FOUND);
        }

        //Lấy profile
        Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new AppException(ErrorCode.PROFILE_NOT_FOUND));

        // 2. lấy plan
        Plan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new AppException(ErrorCode.PLAN_NOT_FOUND));
        //3.update plan vs expertTime
        profile.setPlan(plan);
        if (profile.getExpertTimePlan() == null || profile.getExpertTimePlan().isBefore(LocalDateTime.now())) {
            profile.setExpertTimePlan(LocalDateTime.now().plusDays(plan.getDurationDays()));
        }else
        {
            profile.setExpertTimePlan(profile.getExpertTimePlan().plusDays(plan.getDurationDays()));
        }
        //tạo hóa đơn ms
        Invoice invoice = Invoice.builder()
                .profile(profile)
                .plan(plan)
                .totalPrice(plan.getPrice())
                .paymentMethod("Credit Card")
                .status("completed")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(plan.getDurationDays()))
                .build();
        // cập nhật
        invoice = invoiceRepository.save(invoice);

        return invoiceMapper.toInvoiceResponse(invoice);
    }
}
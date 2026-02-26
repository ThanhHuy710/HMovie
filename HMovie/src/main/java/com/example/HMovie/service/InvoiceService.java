package com.example.HMovie.service;

import com.example.HMovie.dto.request.InvoiceRequest;
import com.example.HMovie.dto.response.InvoiceResponse;
import com.example.HMovie.entity.Invoice;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.InvoiceMapper;
import com.example.HMovie.repository.InvoiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InvoiceService {
    InvoiceRepository invoiceRepository;
    InvoiceMapper invoiceMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Cacheable(value = "invoice_list")
    public List<InvoiceResponse> getAllInvoices() {
        log.info("getAllInvoices: Fetching from Database");
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toInvoiceResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "invoice_detail", key = "#invoiceId")
    public InvoiceResponse getInvoiceById(String invoiceId) {
        log.info("getInvoiceById: Fetching from Database for id {}", invoiceId);
        return invoiceMapper.toInvoiceResponse(invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('USER')")
    @Caching(evict = {
            @CacheEvict(value = "invoice_list", allEntries = true),
            @CacheEvict(value = "invoice_list_by_profile", allEntries = true)
    })
    public InvoiceResponse createInvoice(InvoiceRequest request) {
        log.info("createInvoice: Creating new invoice");
        Invoice invoice = invoiceMapper.toInvoice(request);
        return invoiceMapper.toInvoiceResponse(invoiceRepository.save(invoice));
    }

    @PreAuthorize("hasRole('USER')")
    @Caching(evict = {
            @CacheEvict(value = "invoice_list", allEntries = true),
            @CacheEvict(value = "invoice_detail", key = "#invoiceId"),
            @CacheEvict(value = "invoice_list_by_profile", allEntries = true)
    })
    public InvoiceResponse updateInvoice(String invoiceId, InvoiceRequest request) {
        log.info("updateInvoice: Updating invoice {}", invoiceId);
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_FOUND));
        
        invoiceMapper.updateInvoice(invoice, request);
        return invoiceMapper.toInvoiceResponse(invoiceRepository.save(invoice));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Caching(evict = {
            @CacheEvict(value = "invoice_list", allEntries = true),
            @CacheEvict(value = "invoice_detail", key = "#invoiceId"),
            @CacheEvict(value = "invoice_list_by_profile", allEntries = true)
    })
    public void deleteInvoice(String invoiceId) {
        log.info("deleteInvoice: Deleting invoice {}", invoiceId);
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new AppException(ErrorCode.INVOICE_NOT_FOUND);
        }
        invoiceRepository.deleteById(invoiceId);
    }

    @Cacheable(value = "invoice_list_by_profile", key = "#profileId")
    public List<InvoiceResponse> getInvoicesByProfileId(String profileId) {
        return invoiceRepository.findByProfileProfileId(profileId).stream()
                .map(invoiceMapper::toInvoiceResponse)
                .toList();
    }
}

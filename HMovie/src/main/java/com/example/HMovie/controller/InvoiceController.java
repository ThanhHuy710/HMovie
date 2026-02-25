package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.InvoiceRequest;
import com.example.HMovie.dto.response.InvoiceResponse;
import com.example.HMovie.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InvoiceController {
    InvoiceService invoiceService;

    @GetMapping
    ApiResponse<List<InvoiceResponse>> getAllInvoices() {
        return ApiResponse.<List<InvoiceResponse>>builder()
                .result(invoiceService.getAllInvoices())
                .build();
    }
    @GetMapping("profile/{profileId}")
    ApiResponse<List<InvoiceResponse>> getInvoicesByProfileId(@PathVariable("profileId") String profileId) {
        return ApiResponse.<List<InvoiceResponse>>builder()
                .result(invoiceService.getInvoicesByProfileId(profileId))
                .build();
    }
    @GetMapping("/{invoiceId}")
    ApiResponse<InvoiceResponse> getInvoiceById(@PathVariable("invoiceId") String invoiceId) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(invoiceService.getInvoiceById(invoiceId))
                .build();
    }

    @PostMapping
    ApiResponse<InvoiceResponse> createInvoice(@RequestBody @Valid InvoiceRequest request) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(invoiceService.createInvoice(request))
                .build();
    }

    @PutMapping("/{invoiceId}")
    ApiResponse<InvoiceResponse> updateInvoice(@PathVariable("invoiceId") String invoiceId, @RequestBody @Valid InvoiceRequest request) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(invoiceService.updateInvoice(invoiceId, request))
                .build();
    }

    @DeleteMapping("/{invoiceId}")
    ApiResponse<String> deleteInvoice(@PathVariable("invoiceId") String invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return ApiResponse.<String>builder()
                .result("Invoice has been deleted")
                .build();
    }
}

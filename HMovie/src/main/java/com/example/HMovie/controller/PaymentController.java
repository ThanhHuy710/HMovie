package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.PaymentRequest;
import com.example.HMovie.dto.response.InvoiceResponse;
import com.example.HMovie.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentController {
    PaymentService paymentService;

    @PostMapping("/process")
    ApiResponse<InvoiceResponse> processPayment(@RequestBody @Valid PaymentRequest request) {
        log.info(request.getPlanId());
        return ApiResponse.<InvoiceResponse>builder()
                .result(paymentService.processPayment(request))
                .build();
    }
}

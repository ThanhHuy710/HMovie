package com.example.HMovie.controller;

import com.example.HMovie.dto.ApiResponse;
import com.example.HMovie.dto.request.CartRequest;
import com.example.HMovie.dto.response.CartResponse;
import com.example.HMovie.service.CartService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartController {
    CartService cartService;

    @GetMapping
    ApiResponse<List<CartResponse>> getAllCarts() {
        return ApiResponse.<List<CartResponse>>builder()
                .result(cartService.getAllCarts())
                .build();
    }

    @GetMapping("/{cartId}")
    ApiResponse<CartResponse> getCartById(@PathVariable("cartId") String cartId) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.getCartById(cartId))
                .build();
    }

    @PostMapping
    ApiResponse<CartResponse> createCart(@RequestBody @Valid CartRequest request) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.createCart(request))
                .build();
    }

    @PutMapping("/{cartId}")
    ApiResponse<CartResponse> updateCart(@PathVariable("cartId") String cartId, @RequestBody @Valid CartRequest request) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.updateCart(cartId, request))
                .build();
    }

    @DeleteMapping("/{cartId}")
    ApiResponse<String> deleteCart(@PathVariable("cartId") String cartId) {
        cartService.deleteCart(cartId);
        return ApiResponse.<String>builder()
                .result("Cart item has been deleted")
                .build();
    }
}

package com.example.HMovie.service;

import com.example.HMovie.dto.request.CartRequest;
import com.example.HMovie.dto.response.CartResponse;
import com.example.HMovie.entity.Cart;
import com.example.HMovie.exception.AppException;
import com.example.HMovie.exception.ErrorCode;
import com.example.HMovie.mapper.CartMapper;
import com.example.HMovie.repository.CartRepository;
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
public class CartService {
    CartRepository cartRepository;
    CartMapper cartMapper;

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "cart_list")
    public List<CartResponse> getAllCarts() {
        log.info("getAllCarts: Fetching from Database");
        return cartRepository.findAll().stream()
                .map(cartMapper::toCartResponse)
                .toList();
    }

    @PreAuthorize("hasRole('USER')")
    @Cacheable(value = "cart_detail", key = "#cartId")
    public CartResponse getCartById(String cartId) {
        log.info("getCartById: Fetching from Database for id {}", cartId);
        return cartMapper.toCartResponse(cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('USER')")
    @CacheEvict(value = "cart_list", allEntries = true)
    public CartResponse createCart(CartRequest request) {
        log.info("createCart: Creating new cart item");
        Cart cart = cartMapper.toCart(request);
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    @PreAuthorize("hasRole('USER')")
    @Caching(
            evict = {
                    @CacheEvict(value = "cart_list", allEntries = true),
                    @CacheEvict(value = "cart_detail", key = "#cartId")
            }
    )
    public CartResponse updateCart(String cartId, CartRequest request) {
        log.info("updateCart: Updating cart item {}", cartId);
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        
        cartMapper.updateCart(cart, request);
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    @PreAuthorize("hasRole('USER')")
    @Caching(
            evict = {
                    @CacheEvict(value = "cart_list", allEntries = true),
                    @CacheEvict(value = "cart_detail", key = "#cartId")
            }
    )
    public void deleteCart(String cartId) {
        log.info("deleteCart: Deleting cart item {}", cartId);
        if (!cartRepository.existsById(cartId)) {
            throw new AppException(ErrorCode.CART_NOT_FOUND);
        }
        cartRepository.deleteById(cartId);
    }
}

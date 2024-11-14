package com.nepalfresh.app.service;

import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.request.CartItemRequest;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface CartService {
    Mono<ApiResponse> addItemToCart(Principal customer, CartItemRequest cartItemRequest);
}

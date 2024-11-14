package com.nepalfresh.app.controller;

import com.nepalfresh.app.service.CartService;
import com.nepalfresh.common.constant.ApiConstant;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.request.CartItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.CART)
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping(ApiConstant.ADD)
    Mono<ApiResponse> addToCart(@Valid @RequestBody CartItemRequest cartItemRequest, Principal customer){
        return cartService.addItemToCart(customer, cartItemRequest );
    }
}

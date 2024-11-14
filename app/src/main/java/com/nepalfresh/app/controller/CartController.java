package com.nepalfresh.app.controller;

import com.nepalfresh.app.service.CartService;
import com.nepalfresh.common.constant.ApiConstant;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.ProductCartModel;
import com.nepalfresh.common.dto.request.CartItemRequest;
import com.nepalfresh.common.dto.request.ViewCartRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping(ApiConstant.GET)
    Mono<ApiResponse<?>> listCartItems(Principal customer){
        return cartService.viewItemsInCart(customer);
    }
    @PostMapping(ApiConstant.DELETE)
    Mono<ApiResponse> deleteItemFromCart(Principal customer, ProductCartModel productCartModel){
        return cartService.deleteItemsInCart(customer, productCartModel);
    }
}

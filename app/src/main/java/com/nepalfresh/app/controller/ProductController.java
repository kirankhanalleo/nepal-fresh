package com.nepalfresh.app.controller;

import com.nepalfresh.app.service.ProductService;
import com.nepalfresh.common.constant.ApiConstant;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.SearchParam;
import com.nepalfresh.common.dto.request.ViewProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping(ApiConstant.LIST)
    public Mono<ApiResponse<?>> getAllProduct(@RequestBody SearchParam searchParam){
        return productService.getAllProducts(searchParam);
    }
    @PostMapping(ApiConstant.VIEW)
    public Mono<ApiResponse> viewProduct(@Valid @RequestBody ViewProductRequest viewProductRequest){
        return productService.viewProduct(viewProductRequest);
    }
}

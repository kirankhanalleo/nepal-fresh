package com.nepalfresh.product.controller;

import com.nepalfresh.common.constant.ApiConstant;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.SearchParam;
import com.nepalfresh.product.model.request.CreateProductRequest;
import com.nepalfresh.product.model.request.UpdateProductRequest;
import com.nepalfresh.product.model.request.UpdateProductStatusRequest;
import com.nepalfresh.product.model.request.ViewProductRequest;
import com.nepalfresh.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest){
        return productService.createProduct(createProductRequest);
    }
    @PostMapping(ApiConstant.LIST)
    public Mono<ApiResponse<?>> listAllProduct(@RequestBody SearchParam searchParam){
        return productService.listAllProduct(searchParam);
    }
    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse> updateProductBySlug(@RequestBody @Valid UpdateProductRequest updateProductRequest){
        return productService.updateProduct(updateProductRequest);
    }
    @PostMapping(ApiConstant.VIEW)
    public Mono<ApiResponse<?>> viewProductDetails(@RequestBody @Valid ViewProductRequest viewProductRequest){
        return productService.viewProduct(viewProductRequest);
    }
    @PostMapping(ApiConstant.MARK +"/"+ ApiConstant.OUT_OF_STOCK)
    public Mono<ApiResponse> markAsOutOfStock(@RequestBody @Valid UpdateProductStatusRequest updateProductStatusRequest){
        return productService.markAsOutOfStock(updateProductStatusRequest);
    }
    @PostMapping(ApiConstant.MARK +"/"+ ApiConstant.IN_STOCK)
    public Mono<ApiResponse> markAsInStock(@RequestBody @Valid UpdateProductStatusRequest updateProductStatusRequest){
        return productService.markAsInStock(updateProductStatusRequest);
    }
    @PostMapping(ApiConstant.MARK +"/"+ ApiConstant.UNAVAILABLE)
    public Mono<ApiResponse> markAsUnavailable(@RequestBody @Valid UpdateProductStatusRequest updateProductStatusRequest){
        return productService.markAsUnavailable(updateProductStatusRequest);
    }
}

package com.nepalfresh.product.service;

import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.SearchParam;
import com.nepalfresh.product.model.request.CreateProductRequest;
import com.nepalfresh.product.model.request.UpdateProductRequest;
import com.nepalfresh.product.model.request.UpdateProductStatusRequest;
import com.nepalfresh.product.model.request.ViewProductRequest;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ApiResponse> createProduct(CreateProductRequest createProductRequest);
    Mono<ApiResponse<?>> listAllProduct(SearchParam searchParam);
    Mono<ApiResponse> updateProduct(UpdateProductRequest updateProductRequest);
    Mono<ApiResponse<?>> viewProduct(ViewProductRequest viewProductRequest);
    Mono<ApiResponse> markAsOutOfStock(UpdateProductStatusRequest updateProductStatusRequest);
    Mono<ApiResponse> markAsInStock(UpdateProductStatusRequest updateProductStatusRequest);
    Mono<ApiResponse> markAsUnavailable(UpdateProductStatusRequest updateProductStatusRequest);
}

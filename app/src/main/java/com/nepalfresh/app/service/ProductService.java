package com.nepalfresh.app.service;

import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.SearchParam;
import com.nepalfresh.common.dto.request.ViewProductRequest;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ApiResponse<?>> getAllProducts(SearchParam searchParam);
    Mono<ApiResponse> viewProduct(ViewProductRequest productRequest);
}

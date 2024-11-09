package com.nepalfresh.productCategory.service;

import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.request.CreateProductCategory;
import reactor.core.publisher.Mono;

public interface ProductCategoryService {
    Mono<ApiResponse> createProductCategory(CreateProductCategory createProductCategory);
    Mono<ApiResponse<?>> listProductCategory();
}

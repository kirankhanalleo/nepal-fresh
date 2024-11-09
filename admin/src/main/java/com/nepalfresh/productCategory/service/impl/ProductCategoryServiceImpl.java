package com.nepalfresh.productCategory.service.impl;

import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.request.CreateProductCategory;
import com.nepalfresh.common.dto.request.ListProductCategoryRequest;
import com.nepalfresh.common.mapper.ProductCategoryMapper;
import com.nepalfresh.common.util.ResponseUtil;
import com.nepalfresh.entity.ProductCategory;
import com.nepalfresh.productCategory.service.ProductCategoryService;
import com.nepalfresh.repository.ProductCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;
    @Override
    @Transactional
    public Mono<ApiResponse> createProductCategory(CreateProductCategory createProductCategory) {
        Optional<ProductCategory> existingProductCategory = productCategoryRepository.findByName(createProductCategory.getName());
        if(existingProductCategory.isPresent()){
            return Mono.just(ResponseUtil.getFailureResponse("Product category with same name already exists"));
        }
        ProductCategory productCategory = productCategoryMapper.mapToCreateProductCategory(createProductCategory);
        productCategoryRepository.save(productCategory);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product category created successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> listProductCategory() {
        List<ListProductCategoryRequest> categories = productCategoryMapper.mapToListProductCategory(productCategoryRepository.findAll());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(categories,"Product categories listed successfully"));
    }
}

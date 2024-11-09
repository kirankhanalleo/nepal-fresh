package com.nepalfresh.productCategory.controller;

import com.nepalfresh.common.constant.ApiConstant;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.request.CreateProductCategory;
import com.nepalfresh.productCategory.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiConstant.PRODUCT_CATEGORY)
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse> createProductCategory(@RequestBody @Valid CreateProductCategory createProductCategory){
       return productCategoryService.createProductCategory(createProductCategory);
    }
    @GetMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> listProductCategories(){
        return productCategoryService.listProductCategory();
    }
}

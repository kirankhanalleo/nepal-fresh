package com.nepalfresh.common.mapper;

import com.nepalfresh.common.dto.request.CreateProductCategory;
import com.nepalfresh.common.dto.request.ListProductCategoryRequest;
import com.nepalfresh.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProductCategoryMapper {

    public ProductCategory mapToCreateProductCategory(CreateProductCategory createProductCategory){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(createProductCategory.getName());
        productCategory.setDescription(createProductCategory.getDescription());
        productCategory.setCreatedAt(Timestamp.from(Instant.now()));
        productCategory.setUpdatedAt(Timestamp.from(Instant.now()));
        return productCategory;
    }
    private ListProductCategoryRequest mapToProductCategory(ProductCategory category) {
        ListProductCategoryRequest productCategory = new ListProductCategoryRequest();
        productCategory.setName(category.getName());
        productCategory.setDescription(category.getDescription());
        productCategory.setCreatedAt(category.getCreatedAt());
        productCategory.setUpdatedAt(category.getUpdatedAt());
        return productCategory;
    }

    public List<ListProductCategoryRequest> mapToListProductCategory(List<ProductCategory> productCategories){
        return productCategories.stream()
                .map(this::mapToProductCategory)
                .collect(Collectors.toList());
    }
}

package com.nepalfresh.app.mapper;

import com.nepalfresh.common.dto.ProductDto;
import com.nepalfresh.common.dto.request.ListProductRequest;
import com.nepalfresh.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProductMapper {
    public abstract ListProductRequest mapToProduct(Product product);

    public List<ListProductRequest> mapToListProduct(List<Product> products) {
        return products.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    public abstract ProductDto mapToViewProduct(Product product);
}

package com.nepalfresh.product.mapper;

import com.nepalfresh.common.constant.ProductStatusConstant;
import com.nepalfresh.entity.Product;
import com.nepalfresh.product.model.request.CreateProductRequest;
import com.nepalfresh.product.model.ProductDto;
import com.nepalfresh.product.model.request.ListProductRequest;
import com.nepalfresh.product.model.request.UpdateProductRequest;
import com.nepalfresh.repository.ProductCategoryRepository;
import com.nepalfresh.repository.ProductRepository;
import com.nepalfresh.repository.ProductStatusRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProductMapper {
    @Autowired
    protected ProductCategoryRepository productCategoryRepository;
    @Autowired
    protected ProductStatusRepository productStatusRepository;
    @Autowired
    private ProductRepository productRepository;

    public Product mapToCreateProduct(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setSlug(createProductRequest.getName().toLowerCase().replace(" ", "-"));
        product.setDescription(createProductRequest.getDescription());
        product.setCategory(productCategoryRepository.findByName(createProductRequest.getCategory().getName()).orElseThrow());
        product.setStatus(productStatusRepository.findByName(ProductStatusConstant.IN_STOCK.getName()));
        product.setPrice(createProductRequest.getPrice());
        product.setOfferPrice(createProductRequest.getOfferPrice());
        product.setStockQuantity(createProductRequest.getStockQuantity());
        product.setCreatedAt(Timestamp.from(Instant.now()));
        product.setUpdatedAt(Timestamp.from(Instant.now()));
        return product;
    }

    public abstract ListProductRequest mapToProduct(Product product);

    public List<ListProductRequest> mapToListProduct(List<Product> products) {
        return products.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    public void mapToUpdateProduct(Product product, UpdateProductRequest updateProductRequest){
        product.setDescription(updateProductRequest.getDescription());
        product.setCategory(productCategoryRepository.findByName(updateProductRequest.getCategory().getName()).orElseThrow());
        product.setPrice(updateProductRequest.getPrice());
        product.setOfferPrice(updateProductRequest.getOfferPrice());
        product.setStockQuantity(updateProductRequest.getStockQuantity());
        product.setUpdatedAt(Timestamp.from(Instant.now()));
        productRepository.save(product);
    }

    public abstract ProductDto mapToViewProduct(Product product);

    public void mapToOutOfStock(Product product){
        product.setStatus(productStatusRepository.findByName(ProductStatusConstant.OUT_OF_STOCK.getName()));
        productRepository.save(product);
    }

    public void mapToInStock(Product product){
        product.setStatus(productStatusRepository.findByName(ProductStatusConstant.IN_STOCK.getName()));
        productRepository.save(product);
    }
}

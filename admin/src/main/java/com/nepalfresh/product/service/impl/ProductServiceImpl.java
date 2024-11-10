package com.nepalfresh.product.service.impl;

import com.nepalfresh.common.constant.ProductStatusConstant;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.PageableResponse;
import com.nepalfresh.common.dto.SearchParam;
import com.nepalfresh.common.dto.SearchResponseWithMapperBuilder;
import com.nepalfresh.common.service.SearchResponse;
import com.nepalfresh.common.util.ResponseUtil;
import com.nepalfresh.entity.Product;
import com.nepalfresh.product.mapper.ProductMapper;
import com.nepalfresh.product.model.request.*;
import com.nepalfresh.product.model.ProductDto;
import com.nepalfresh.product.service.ProductService;
import com.nepalfresh.repository.product.ProductRepository;
import com.nepalfresh.repository.product.ProductSearchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductSearchRepository productSearchRepository;
    private final SearchResponse searchResponse;

    @Override
    @Transactional
    public Mono<ApiResponse> createProduct(CreateProductRequest createProductRequest) {
        logger.info("Attempting to create a product with name: {}", createProductRequest.getName());

        Optional<Product> existingProduct = productRepository.findByName(createProductRequest.getName());
        if(existingProduct.isPresent()) {
            logger.warn("Product creation failed. Product with name '{}' already exists", createProductRequest.getName());
            return Mono.just(ResponseUtil.getFailureResponse("Product with the same name already exists"));
        }
        try{
            Product product = productMapper.mapToCreateProduct(createProductRequest);
            productRepository.save(product);
            logger.info("Product created successfully with name : {}", product.getName());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product Created Successfully"));
        }
        catch(DataIntegrityViolationException e){
            logger.error("Data integrity violation while creating product: {}", createProductRequest.getName(), e);
            return Mono.just(ResponseUtil.getFailureResponse("Product creation failed due to data integrity violation"));
        }
        catch (Exception e){
            logger.error("Unexpected error occurred while creating product: {}", createProductRequest.getName(), e);
            return Mono.just(ResponseUtil.getFailureResponse("Product creation failed. Please try again later."));
        }
    }

    @Override
    public Mono<ApiResponse<?>> listAllProduct(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Product, ListProductRequest>  responseBuilder= SearchResponseWithMapperBuilder.<Product, ListProductRequest>builder()
                .count(productSearchRepository::count)
                .searchData(productSearchRepository::getAll)
                .mapperFunction(this.productMapper::mapToListProduct)
                .searchParam(searchParam)
                .build();
        PageableResponse<ListProductRequest> products = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(products,"Product listed successfully"));
    }

    @Override
    @Transactional
    public Mono<ApiResponse> updateProduct(UpdateProductRequest updateProductRequest) {
        logger.info("Attempting to update a product with slug: {}", updateProductRequest.getSlug());

        Optional<Product> existingProduct = productRepository.findBySlug(updateProductRequest.getSlug());
        if (existingProduct.isEmpty()){
            return Mono.just(ResponseUtil.getFailureResponse("Product does not exist"));
        }
        try{
            productMapper.mapToUpdateProduct(existingProduct.get(), updateProductRequest);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product Updated Successfully"));
        }
        catch(DataIntegrityViolationException e){
            return Mono.just(ResponseUtil.getFailureResponse("Product update failed due to data integrity violation"));
        }
        catch (Exception e){
            return Mono.just(ResponseUtil.getFailureResponse("Product update failed. Please try again later."));
        }
    }

    @Override
    @Transactional
    public Mono<ApiResponse<?>> viewProduct(ViewProductRequest viewProductRequest) {
        Optional<Product> product = productRepository.findBySlug(viewProductRequest.getSlug());
        if(product.isPresent()){
            ProductDto viewProduct = productMapper.mapToViewProduct(product.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(viewProduct,"Product fetched successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("No product found"));
    }

    @Override
    @Transactional
    public Mono<ApiResponse> markAsOutOfStock(UpdateProductStatusRequest updateProductStatusRequest) {
       Optional<Product> existingProduct = productRepository.findBySlug(updateProductStatusRequest.getSlug());
       if (existingProduct.isEmpty()){
           return Mono.just(ResponseUtil.getFailureResponse("Product does not exist"));
       }
       try {
           if (ProductStatusConstant.OUT_OF_STOCK.getName().equals(existingProduct.get().getStatus().getName())){
               return Mono.just(ResponseUtil.getFailureResponse("Product already marked as out of stock"));
           }
           productMapper.mapToOutOfStock(existingProduct.get());
           return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product marked as out of stock"));
       }
       catch(DataIntegrityViolationException e){
           return Mono.just(ResponseUtil.getFailureResponse("Product update failed due to data integrity violation"));
       }
       catch (Exception e){
           return Mono.just(ResponseUtil.getFailureResponse("Product update failed. Please try again later."));
       }
    }

    @Override
    @Transactional
    public Mono<ApiResponse> markAsInStock(UpdateProductStatusRequest updateProductStatusRequest) {
        Optional<Product> existingProduct = productRepository.findBySlug(updateProductStatusRequest.getSlug());
        if (existingProduct.isEmpty()){
            return Mono.just(ResponseUtil.getFailureResponse("Product does not exist"));
        }
        try{
            if (ProductStatusConstant.IN_STOCK.getName().equals(existingProduct.get().getStatus().getName())){
                return Mono.just(ResponseUtil.getFailureResponse("Product already marked as in stock"));
            }
            productMapper.mapToInStock(existingProduct.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product marked as in stock"));
        }
        catch(DataIntegrityViolationException e){
            return Mono.just(ResponseUtil.getFailureResponse("Product update failed due to data integrity violation"));
        }
        catch (Exception e){
            return Mono.just(ResponseUtil.getFailureResponse("Product update failed. Please try again later."));
        }
    }

    @Override
    @Transactional
    public Mono<ApiResponse> markAsUnavailable(UpdateProductStatusRequest updateProductStatusRequest) {
        Optional<Product> existingProduct = productRepository.findBySlug(updateProductStatusRequest.getSlug());
        if (existingProduct.isEmpty()){
            return Mono.just(ResponseUtil.getFailureResponse("Product does not exist"));
        }
        try{
            if (ProductStatusConstant.UNAVAILABLE.getName().equals(existingProduct.get().getStatus().getName())){
                return Mono.just(ResponseUtil.getFailureResponse("Product already marked as unavailable"));
            }
            productMapper.mapToUnavailable(existingProduct.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product marked as unavailable"));
        }
        catch(DataIntegrityViolationException e){
            return Mono.just(ResponseUtil.getFailureResponse("Product update failed due to data integrity violation"));
        }
        catch (Exception e){
            return Mono.just(ResponseUtil.getFailureResponse("Product update failed. Please try again later."));
        }
    }
}

package com.nepalfresh.product.service.impl;

import com.nepalfresh.common.constant.ProductStatusConstant;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.util.ResponseUtil;
import com.nepalfresh.entity.Product;
import com.nepalfresh.product.mapper.ProductMapper;
import com.nepalfresh.product.model.request.*;
import com.nepalfresh.product.model.ProductDto;
import com.nepalfresh.product.service.ProductService;
import com.nepalfresh.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Mono<ApiResponse> createProduct(CreateProductRequest createProductRequest) {
        Optional<Product> existingProduct = productRepository.findByName(createProductRequest.getName());
        if(existingProduct.isPresent())
            return Mono.just(ResponseUtil.getFailureResponse("Product with the same name already exists"));
        try{
            Product product = productMapper.mapToCreateProduct(createProductRequest);
            productRepository.save(product);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product Created Successfully"));
        }
        catch(DataIntegrityViolationException e){
            return Mono.just(ResponseUtil.getFailureResponse("Product creation failed due to data integrity violation"));
        }
        catch (Exception e){
            return Mono.just(ResponseUtil.getFailureResponse("Product creation failed. Please try again later."));
        }
    }

    @Override
    public Mono<ApiResponse<?>> listAllProduct() {
        List<ListProductRequest> products = productMapper.mapToListProduct(productRepository.findAll());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(products,"Product listed successfully"));

    }

    @Override
    @Transactional
    public Mono<ApiResponse> updateProduct(UpdateProductRequest updateProductRequest) {
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
}

package com.nepalfresh.app.service.impl;

import com.nepalfresh.common.dto.*;
import com.nepalfresh.common.dto.request.ListProductRequest;
import com.nepalfresh.app.mapper.ProductMapper;
import com.nepalfresh.app.service.ProductService;
import com.nepalfresh.common.constant.ProductStatusConstant;
import com.nepalfresh.common.dto.request.ViewProductRequest;
import com.nepalfresh.common.service.SearchResponse;
import com.nepalfresh.common.util.ResponseUtil;
import com.nepalfresh.entity.Product;
import com.nepalfresh.repository.product.ProductRepository;
import com.nepalfresh.repository.product.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import static com.nepalfresh.common.constant.SearchParamConstant.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductSearchRepository productSearchRepository;
    private final ProductMapper productMapper;
    private final SearchResponse searchResponse;
    private final ProductRepository productRepository;

    @Override
    public Mono<ApiResponse<?>> getAllProducts(SearchParam searchParam) {
        searchParam.getParam().put(STATUS, ProductStatusConstant.IN_STOCK.getName());
        SearchResponseWithMapperBuilder<Product, ListProductRequest> responseBuilder= SearchResponseWithMapperBuilder.<Product, ListProductRequest>builder()
                .count(productSearchRepository::count)
                .searchData(productSearchRepository::getAll)
                .mapperFunction(this.productMapper::mapToListProduct)
                .searchParam(searchParam)
                .build();
        List<ListProductRequest> productList = searchResponse.getSearchResponse(responseBuilder).getRecords();
        List<ListProductRequest> filteredProducts = productList.stream()
                .filter(productDto -> ProductStatusConstant.IN_STOCK.getName().equals(productDto.getStatus().getName())
                        || ProductStatusConstant.OUT_OF_STOCK.getName().equals(productDto.getStatus().getName()))
                .toList();
        if (filteredProducts.isEmpty()) {
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(null, "No product found"));
        }
        PageableResponse<ListProductRequest> response = new PageableResponse<>();
        response.setRecords(filteredProducts);
        response.setTotal(filteredProducts.size());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Product listed successfully"));
    }

    @Override
    public Mono<ApiResponse> viewProduct(ViewProductRequest viewProductRequest) {
        Optional<Product> product = productRepository.findBySlug(viewProductRequest.getSlug());
        if(product.isPresent()){
            ProductDto viewProduct = productMapper.mapToViewProduct(product.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(viewProduct,"Product fetched successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("No product found"));
    }
}

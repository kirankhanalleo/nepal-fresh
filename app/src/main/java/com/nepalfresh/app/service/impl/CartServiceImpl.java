package com.nepalfresh.app.service.impl;

import com.nepalfresh.app.mapper.CartMapper;
import com.nepalfresh.app.service.CartService;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.request.CartItemRequest;
import com.nepalfresh.common.util.ResponseUtil;
import com.nepalfresh.entity.Customer;
import com.nepalfresh.entity.Product;
import com.nepalfresh.repository.customer.CustomerRepository;
import com.nepalfresh.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Mono<ApiResponse> addItemToCart(Principal customer, CartItemRequest cartItemRequest) {
        if(customer == null || customer.getName()==null){
            logger.error("Adding product to cart failed since user is not logged in");
            return Mono.just(ResponseUtil.getFailureResponse("You must login to continue."));
        }
        Optional<Customer> cartCustomer = customerRepository.findByUsername(customer.getName());
        Optional<Product> productToAdd = productRepository.findBySlug(cartItemRequest.getProduct().getProductSlug());
        if(productToAdd.isEmpty()){
            logger.warn("Adding product to cart failed. Product with slug '{}' does not exist.", cartItemRequest.getProduct().getProductSlug());
            return Mono.just(ResponseUtil.getFailureResponse("The product does not exist"));
        }
        cartMapper.mapAddToCart(cartItemRequest,cartCustomer.get(),productToAdd.get());
        logger.info("Product with slug '{}' successfully added to cart for customer '{}'",cartItemRequest.getProduct().getProductSlug(), cartCustomer);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product added to cart."));
    }
}

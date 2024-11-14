package com.nepalfresh.app.service.impl;

import com.nepalfresh.app.mapper.CartMapper;
import com.nepalfresh.app.service.CartService;
import com.nepalfresh.common.dto.ApiResponse;
import com.nepalfresh.common.dto.ProductCartModel;
import com.nepalfresh.common.dto.request.CartItemRequest;
import com.nepalfresh.common.dto.request.ViewCartRequest;
import com.nepalfresh.common.util.ResponseUtil;
import com.nepalfresh.entity.Cart;
import com.nepalfresh.entity.CartItem;
import com.nepalfresh.entity.Customer;
import com.nepalfresh.entity.Product;
import com.nepalfresh.repository.cart.CartItemRepository;
import com.nepalfresh.repository.cart.CartRepository;
import com.nepalfresh.repository.customer.CustomerRepository;
import com.nepalfresh.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

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
        Optional<CartItem> cartItem = cartItemRepository.findCartItemByProductId(productToAdd.get().getId());
        if(cartItem.isPresent()){
            logger.warn("Adding product to cart failed. Product with slug '{}' already exist in cart.", cartItemRequest.getProduct().getProductSlug());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("This product has been already added to cart."));
        }
        cartMapper.mapAddToCart(cartItemRequest,cartCustomer.get(),productToAdd.get());
        logger.info("Product with slug '{}' successfully added to cart for customer '{}'",cartItemRequest.getProduct().getProductSlug(), cartCustomer);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product added to cart."));
    }

    @Override
    public Mono<ApiResponse<?>> viewItemsInCart(Principal customer) {
        if(customer == null || customer.getName()==null){
            logger.error("Viewing cart failed since user is not logged in");
            return Mono.just(ResponseUtil.getFailureResponse("You must login to continue."));
        }
        Optional<Customer> cartCustomerName = customerRepository.findByUsername(customer.getName());
        Long cartCustomerId = cartCustomerName.get().getId();
        Cart cart = cartRepository.findCartByCustomerId(cartCustomerId);
        List<ViewCartRequest> cartItems  = cartMapper.mapToViewCart(cartItemRepository.findCartItemsByCartId(cart.getId()));
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(cartItems,"Cart Items Listed Successfully"));
    }

    @Override
    public Mono<ApiResponse> deleteItemsInCart(Principal customer, ProductCartModel productCartModel) {
        if(customer == null || customer.getName()==null){
            logger.error("Removing product failed since user is not logged in");
            return Mono.just(ResponseUtil.getFailureResponse("You must login to continue."));
        }
        Optional<Product> product = productRepository.findBySlug(productCartModel.getProductSlug());
        Optional<CartItem> cartItem = cartItemRepository.findCartItemByProductId(product.get().getId());
        if(cartItem.isEmpty()){
            return Mono.just(ResponseUtil.getFailureResponse("The item does not exist"));
        }
        cartItemRepository.deleteById(cartItem.get().getId());
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product removed from cart"));
    }
}

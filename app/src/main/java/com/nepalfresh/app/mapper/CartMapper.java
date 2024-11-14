package com.nepalfresh.app.mapper;

import com.nepalfresh.common.dto.request.CartItemRequest;
import com.nepalfresh.entity.Cart;
import com.nepalfresh.entity.CartItem;
import com.nepalfresh.entity.Customer;
import com.nepalfresh.entity.Product;
import com.nepalfresh.repository.cart.CartItemRepository;
import com.nepalfresh.repository.cart.CartRepository;
import com.nepalfresh.repository.customer.CustomerRepository;
import com.nepalfresh.repository.product.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CartMapper {
    @Autowired
    protected CartRepository cartRepository;
    @Autowired
    protected CartItemRepository cartItemRepository;
    @Autowired
    protected CustomerRepository customerRepository;

    public CartItem mapAddToCart(CartItemRequest cartItemRequest, Customer customer, Product product){
        Cart cart = cartRepository.findCartByCustomerId(customer.getId());
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setAddedAt(Timestamp.from(Instant.now()));
        return cartItemRepository.save(cartItem);
    }
}

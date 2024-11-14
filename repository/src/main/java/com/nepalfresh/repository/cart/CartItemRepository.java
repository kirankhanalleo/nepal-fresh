package com.nepalfresh.repository.cart;

import com.nepalfresh.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemsByCartId(Long id);
    Optional<CartItem> findCartItemByProductId(Long id);
}

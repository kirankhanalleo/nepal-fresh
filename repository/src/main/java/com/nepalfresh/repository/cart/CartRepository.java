package com.nepalfresh.repository.cart;

import com.nepalfresh.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByCustomerId(Long id);
}

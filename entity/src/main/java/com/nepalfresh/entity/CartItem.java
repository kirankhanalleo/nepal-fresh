package com.nepalfresh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="cart_items")
public class CartItem extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name="cart_id",nullable = false, referencedColumnName = "id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false, referencedColumnName = "id")
    private Product product;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

    @Column(name="added_at")
    private Timestamp addedAt;
}

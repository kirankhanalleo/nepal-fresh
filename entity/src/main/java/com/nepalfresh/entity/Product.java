package com.nepalfresh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name="products")
public class Product extends AbstractEntity{

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="slug", nullable = false)
    private String slug;

    @Column(name="description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="product_category",nullable = false, referencedColumnName = "id")
    private ProductCategory category;

    @Column(name="price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name="offer_price", nullable = true, precision = 10, scale = 2)
    private BigDecimal offerPrice;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @JoinColumn(name="status",nullable = false, referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductStatus status;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;
}

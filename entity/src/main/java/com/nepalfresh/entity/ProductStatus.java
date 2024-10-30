package com.nepalfresh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="product_status")
public class ProductStatus extends AbstractEntity{

    @Column(name="name", nullable = false, unique = true)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;
}

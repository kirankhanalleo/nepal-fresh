package com.nepalfresh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="carts")
public class Cart extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name="customer_id",nullable = false, referencedColumnName = "id")
    private Customer customer;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;
}

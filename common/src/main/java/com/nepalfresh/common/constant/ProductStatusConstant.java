package com.nepalfresh.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatusConstant {
    IN_STOCK("In Stock", "Product is in stock."),
    OUT_OF_STOCK("Out of Stock", "Product is out of stock."),
    UNAVAILABLE("Unavailable", "Product is unavailable.");

    private final String name;
    private final String description;
}

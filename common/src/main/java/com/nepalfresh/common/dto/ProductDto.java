package com.nepalfresh.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto extends ModelBase {
    private String name;
    private String description;
    private Integer categoryId;
    private BigDecimal price;
    private Integer stockQuantity;
    private Integer status;
}

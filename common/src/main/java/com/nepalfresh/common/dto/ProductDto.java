package com.nepalfresh.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto extends ModelBase {
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private BigDecimal offerPrice;
    private ProductStatusDto status;
}

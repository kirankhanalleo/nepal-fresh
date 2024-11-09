package com.nepalfresh.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryDto extends ModelBase {
    private String name;
    private String description;
}

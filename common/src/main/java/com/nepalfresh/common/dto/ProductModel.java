package com.nepalfresh.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel extends ModelBase {
    @NotBlank(message = "Product is required")
    private String productSlug;
}

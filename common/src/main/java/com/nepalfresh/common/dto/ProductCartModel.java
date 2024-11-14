package com.nepalfresh.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCartModel extends ModelBase {
    @NotBlank(message = "Product is required")
    private String productSlug;
}

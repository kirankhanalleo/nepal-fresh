package com.nepalfresh.common.dto.request;

import com.nepalfresh.common.dto.ModelBase;
import com.nepalfresh.common.dto.ProductModel;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest extends ModelBase {
    @NotNull(message = "Product is required")
    private ProductModel product;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
}

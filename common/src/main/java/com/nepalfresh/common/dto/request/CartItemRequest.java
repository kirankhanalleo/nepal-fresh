package com.nepalfresh.common.dto.request;

import com.nepalfresh.common.dto.ModelBase;
import com.nepalfresh.common.dto.ProductCartModel;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest extends ModelBase {
    @NotNull(message = "Product is required")
    private ProductCartModel product;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
}

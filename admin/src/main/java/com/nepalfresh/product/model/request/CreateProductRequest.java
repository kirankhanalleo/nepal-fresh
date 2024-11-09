package com.nepalfresh.product.model.request;

import com.nepalfresh.common.dto.ModelBase;
import com.nepalfresh.entity.Status;
import com.nepalfresh.productCategory.model.ProductCategoryModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductRequest extends ModelBase {
    @NotBlank(message="Product name is required")
    private String name;
    @NotBlank(message = "Product description is required")
    private String description;
    @Valid
    private ProductCategoryModel category;
    @NotNull(message = "Product price is required")
    @Positive(message = "Price must be positive")
    @Min(value = 1, message = "Price must be greater than 0")
    private BigDecimal price;
    @Positive(message = "Offer price must be positive")
    @Min(value = 1, message = "Offer price must be greater than 0")
    private BigDecimal offerPrice;
    @NotNull(message="Stock quantity is required")
    @Positive(message = "Stock quantity be positive")
    @Min(value = 1, message = "Stock quantity be greater than 0")
    private Integer stockQuantity;
}

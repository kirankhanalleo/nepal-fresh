package com.nepalfresh.product.model.request;

import com.nepalfresh.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductStatusRequest extends ModelBase {
    @NotBlank(message="Product slug is required")
    private String slug;
}

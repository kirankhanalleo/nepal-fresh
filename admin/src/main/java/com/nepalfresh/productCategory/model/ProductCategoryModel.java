package com.nepalfresh.productCategory.model;

import com.nepalfresh.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryModel extends ModelBase {
    @NotBlank(message = "Please select product category")
    private String name;
}

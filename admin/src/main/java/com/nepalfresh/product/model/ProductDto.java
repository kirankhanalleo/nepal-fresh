package com.nepalfresh.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nepalfresh.common.dto.ModelBase;
import com.nepalfresh.common.dto.ProductStatusDto;
import com.nepalfresh.productCategory.model.ProductCategoryModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class ProductDto extends ModelBase {
    private String name;
    private String slug;
    private String description;
    private ProductCategoryModel category;
    private ProductStatusDto status;
    private BigDecimal price;
    private BigDecimal offerPrice;
    private Integer stockQuantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "Asia/Kathmandu")
    private Timestamp createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "Asia/Kathmandu")
    private Timestamp updatedAt;
}

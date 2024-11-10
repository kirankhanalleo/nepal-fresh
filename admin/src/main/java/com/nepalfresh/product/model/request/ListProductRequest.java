package com.nepalfresh.product.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nepalfresh.common.dto.ModelBase;
import com.nepalfresh.common.dto.ProductStatusDto;
import com.nepalfresh.productCategory.model.ProductCategoryModel;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class ListProductRequest extends ModelBase {
    private String name;
    private String slug;
    private ProductCategoryModel category;
    private ProductStatusDto status;
    private Integer stockQuantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "Asia/Kathmandu")
    private Timestamp createdAt;
}

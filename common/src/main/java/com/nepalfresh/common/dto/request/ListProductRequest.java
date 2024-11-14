package com.nepalfresh.common.dto.request;

import com.nepalfresh.common.dto.ModelBase;
import com.nepalfresh.common.dto.ProductStatusDto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ListProductRequest extends ModelBase {
    private String name;
    private String slug;
    private ProductStatusDto status;
    private BigDecimal price;
    private BigDecimal offerPrice;
}

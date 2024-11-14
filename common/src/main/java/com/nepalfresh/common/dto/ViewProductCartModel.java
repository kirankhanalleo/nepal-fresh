package com.nepalfresh.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewProductCartModel extends ModelBase{
    private String name;
    private String slug;
    private String price;
    private ProductStatusDto status;
}

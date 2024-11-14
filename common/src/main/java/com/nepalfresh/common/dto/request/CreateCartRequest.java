package com.nepalfresh.common.dto.request;

import com.nepalfresh.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCartRequest extends ModelBase {
    private List<CartItemRequest> items;
}

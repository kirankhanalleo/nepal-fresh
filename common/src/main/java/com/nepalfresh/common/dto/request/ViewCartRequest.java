package com.nepalfresh.common.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nepalfresh.common.dto.ModelBase;
import com.nepalfresh.common.dto.ViewProductCartModel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ViewCartRequest extends ModelBase {
    private ViewProductCartModel product;
    private Integer quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "Asia/Kathmandu")
    private Timestamp addedAt;
}

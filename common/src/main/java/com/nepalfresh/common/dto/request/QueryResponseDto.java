package com.nepalfresh.common.dto.request;

import com.nepalfresh.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class QueryResponseDto extends ModelBase {
    private String message;
    private String code;
    private Date responseDate;
}

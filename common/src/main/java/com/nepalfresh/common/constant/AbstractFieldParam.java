package com.nepalfresh.common.constant;

import com.nepalfresh.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractFieldParam extends ModelBase {

    protected String fieldKey;

    protected String fieldOperator;
}

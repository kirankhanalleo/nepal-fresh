package com.nepalfresh.common.dto.request;

import com.nepalfresh.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewEventRequest extends ModelBase {

    @NotBlank(message = "Code cannot be blank")
    private String code;
}

package com.nepalfresh.common.dto.request;

import com.nepalfresh.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewWardDetailRequest extends ModelBase {
    @NotBlank(message = "Please enter ward number")
    private String wardNumber;
}

package com.nepalfresh.common.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductCategory {
    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message="Description is required")
    private String description;
}

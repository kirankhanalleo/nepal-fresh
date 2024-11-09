package com.nepalfresh.common.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class ListProductCategoryRequest {
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

package com.nepalfresh.common.service;

import com.nepalfresh.common.dto.PageableResponse;
import com.nepalfresh.common.dto.SearchResponseBuilder;
import com.nepalfresh.common.dto.SearchResponseWithMapperBuilder;

import java.util.List;

public interface SearchResponse {

    <T> PageableResponse<T> getSearchResponse(List<T> record, Integer totalCount);

    <R> PageableResponse<R> getSearchResponse(SearchResponseBuilder<R> searchResponseBuilder);

    <T, R> PageableResponse<R> getSearchResponse(SearchResponseWithMapperBuilder<T, R> responseWithMapperBuilder);
}

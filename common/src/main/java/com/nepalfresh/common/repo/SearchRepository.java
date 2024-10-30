package com.nepalfresh.common.repo;

import com.nepalfresh.common.dto.SearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository<T> {

    Long count(SearchParam searchParam);
    List<T> getAll(SearchParam searchParam);
}

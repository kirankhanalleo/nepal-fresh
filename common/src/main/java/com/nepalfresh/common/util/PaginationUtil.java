package com.nepalfresh.common.util;

import com.nepalfresh.common.dto.SearchParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.nepalfresh.common.util.MathUtil.zeroIfNegative;

public class PaginationUtil {
    public static Pageable getPageable(SearchParam searchParam) {
        return getPageable(zeroIfNegative(searchParam.getFirstRow() - 1), searchParam.getPageSize());
    }

    public static Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

}

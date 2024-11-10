package com.nepalfresh.repository.product;

import com.nepalfresh.common.repo.SearchRepository;
import com.nepalfresh.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSearchRepository extends SearchRepository<Product> {
}

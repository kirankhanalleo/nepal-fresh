package com.nepalfresh.repository.productstatus;

import com.nepalfresh.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
    ProductStatus findByName(String name);
}

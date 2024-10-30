package com.nepalfresh.common.repo;

import com.nepalfresh.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
    Status findByName(String name);
}

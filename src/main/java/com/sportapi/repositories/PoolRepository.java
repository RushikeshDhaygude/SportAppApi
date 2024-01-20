package com.sportapi.repositories;

import com.sportapi.model.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolRepository extends JpaRepository<Pool, Long> {
    // Add custom queries if needed
}

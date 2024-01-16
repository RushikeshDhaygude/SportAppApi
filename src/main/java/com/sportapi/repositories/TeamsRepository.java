package com.sportapi.repositories;

import com.sportapi.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {
    // Add custom queries if needed
}

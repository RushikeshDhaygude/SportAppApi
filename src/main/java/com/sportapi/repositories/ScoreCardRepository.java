package com.sportapi.repositories;

import com.sportapi.model.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {
    // Add custom queries if needed
}

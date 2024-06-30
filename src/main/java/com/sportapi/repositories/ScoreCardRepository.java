package com.sportapi.repositories;

import com.sportapi.model.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {
    List<ScoreCard> findByStatus(String status);

    List<ScoreCard> findByEventIdAndStatus(Long eventId, String aFinal);
    // Add custom queries if needed
}

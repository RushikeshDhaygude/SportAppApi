package com.sportapi.services;

import com.sportapi.model.ScoreCard;
import com.sportapi.repositories.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ScoreCardService {
    
    List<ScoreCard> getAllScoreCards();

    Optional<ScoreCard> getScoreCardById(Long id);

    ScoreCard saveScoreCard(ScoreCard scoreCard);

    void deleteScoreCard(Long id);

    List<ScoreCard> getFinalScoreCards();


    List<ScoreCard> getFinalScoreCardsByEventId(Long eventId);
}

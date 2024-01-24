package com.sportapi.services;

import com.sportapi.model.ScoreCard;

import java.util.List;
import java.util.Optional;

public interface ScoreCardService {

    List<ScoreCard> getAllScoreCards();

    Optional<ScoreCard> getScoreCardById(Long id);

    ScoreCard saveScoreCard(ScoreCard scoreCard);

    void deleteScoreCard(Long id);
}

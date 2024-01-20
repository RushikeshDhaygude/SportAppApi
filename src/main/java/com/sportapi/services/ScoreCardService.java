package com.sportapi.services;

import com.sportapi.model.ScoreCard;

import java.util.List;

public interface ScoreCardService {

    ScoreCard createScoreCard(ScoreCard scoreCard);

    ScoreCard getScoreCardById(Long scoreCardId);

    List<ScoreCard> getAllScoreCards();

    ScoreCard updateScoreCard(ScoreCard scoreCard);

    boolean deleteScoreCard(Long scoreCardId);

    // Add other service methods as needed
}

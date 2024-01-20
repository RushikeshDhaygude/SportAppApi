package com.sportapi.services.Impl;

import com.sportapi.model.ScoreCard;
import com.sportapi.repositories.ScoreCardRepository;
import com.sportapi.services.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreCardServiceImpl implements ScoreCardService {

    @Autowired
    private ScoreCardRepository scoreCardRepository;

    @Override
    public ScoreCard createScoreCard(ScoreCard scoreCard) {
        return scoreCardRepository.save(scoreCard);
    }

    @Override
    public ScoreCard getScoreCardById(Long scoreCardId) {
        Optional<ScoreCard> optionalScoreCard = scoreCardRepository.findById(scoreCardId);
        return optionalScoreCard.orElse(null);
    }

    @Override
    public List<ScoreCard> getAllScoreCards() {
        return scoreCardRepository.findAll();
    }

    @Override
    public ScoreCard updateScoreCard(ScoreCard scoreCard) {
        return scoreCardRepository.save(scoreCard);
    }

    @Override
    public boolean deleteScoreCard(Long scoreCardId) {
        if (scoreCardRepository.existsById(scoreCardId)) {
            scoreCardRepository.deleteById(scoreCardId);
            return true;
        }
        return false;
    }

    // Add other service methods as needed
}

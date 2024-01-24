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
    public List<ScoreCard> getAllScoreCards() {
        try {
            return scoreCardRepository.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error retrieving all score cards", e); // or handle it as appropriate
        }
    }

    @Override
    public Optional<ScoreCard> getScoreCardById(Long id) {
        try {
            return scoreCardRepository.findById(id);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error retrieving score card by ID", e); // or handle it as appropriate
        }
    }

    @Override
    public ScoreCard saveScoreCard(ScoreCard scoreCard) {
        try {
            // Additional logic if needed
            return scoreCardRepository.save(scoreCard);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error saving score card", e); // or handle it as appropriate
        }
    }

    @Override
    public void deleteScoreCard(Long id) {
        try {
            scoreCardRepository.deleteById(id);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error deleting score card", e); // or handle it as appropriate
        }
    }
}

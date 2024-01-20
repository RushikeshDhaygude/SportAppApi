package com.sportapi.controllers;

import com.sportapi.model.ScoreCard;
import com.sportapi.services.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/scorecards")
public class ScoreCardController {

    private final ScoreCardService scoreCardService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ScoreCardController(ScoreCardService scoreCardService, SimpMessagingTemplate messagingTemplate) {
        this.scoreCardService = scoreCardService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping
    public ResponseEntity<ScoreCard> createScoreCard(@RequestBody ScoreCard scoreCard) {
        ScoreCard createdScoreCard = scoreCardService.createScoreCard(scoreCard);

        // Send WebSocket message to update clients
        messagingTemplate.convertAndSend("/topic/scorecards", createdScoreCard);

        return new ResponseEntity<>(createdScoreCard, HttpStatus.CREATED);
    }

    @PutMapping("/{scoreCardId}")
    public ResponseEntity<ScoreCard> updateScoreCard(@PathVariable Long scoreCardId, @RequestBody ScoreCard updatedScoreCard) {
        ScoreCard existingScoreCard = scoreCardService.getScoreCardById(scoreCardId);

        if (existingScoreCard != null) {

            ScoreCard updatedScoreCardResult = scoreCardService.updateScoreCard(existingScoreCard);

            // Send WebSocket message to update clients
            messagingTemplate.convertAndSend("/topic/scorecards", updatedScoreCardResult);

            return new ResponseEntity<>(updatedScoreCardResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{scoreCardId}")
    public ResponseEntity<ScoreCard> getScoreCardById(@PathVariable Long scoreCardId) {
        ScoreCard scoreCard = scoreCardService.getScoreCardById(scoreCardId);
        if (scoreCard != null) {
            return new ResponseEntity<>(scoreCard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ScoreCard>> getAllScoreCards() {
        List<ScoreCard> scoreCards = scoreCardService.getAllScoreCards();
        return new ResponseEntity<>(scoreCards, HttpStatus.OK);
    }

    @DeleteMapping("/{scoreCardId}")
    public ResponseEntity<Void> deleteScoreCard(@PathVariable Long scoreCardId) {
        if (scoreCardService.deleteScoreCard(scoreCardId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

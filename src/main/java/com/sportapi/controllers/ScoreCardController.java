package com.sportapi.controllers;

import com.sportapi.model.ScoreCard;
import com.sportapi.model.Teams;
import com.sportapi.services.ScoreCardService;
import com.sportapi.services.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scorecards")
public class ScoreCardController {

    @Autowired
    private ScoreCardService scoreCardService;

    @Autowired
    private TeamsService teamService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public List<ScoreCard> getAllScoreCards() {
        try {
            return scoreCardService.getAllScoreCards();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error retrieving score cards", e); // or handle it as appropriate
        }
    }

    @GetMapping("/{id}")
    public ScoreCard getScoreCardById(@PathVariable Long id) {
        try {
            return scoreCardService.getScoreCardById(id).orElse(null);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error retrieving score card by ID", e); // or handle it as appropriate
        }
    }

    @PostMapping
    public ScoreCard saveScoreCard(@RequestBody ScoreCard scoreCard) {
        try {
            // Fetch team details based on team IDs
            Teams team1 = teamService.getTeamById(scoreCard.getTeam1().getId());
            Teams team2 = teamService.getTeamById(scoreCard.getTeam2().getId());

            if (team1 != null && team2 != null) {
                // Set the fetched teams in the score card
                scoreCard.setTeam1(team1);
                scoreCard.setTeam2(team2);

                // Additional logic if needed
                ScoreCard savedScoreCard = scoreCardService.saveScoreCard(scoreCard);
                notifyScoreCardUpdate(savedScoreCard);
                return savedScoreCard;
            } else {
                // Handle the case where team details are not found
                throw new IllegalArgumentException("Invalid team IDs");
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error saving score card", e); // or handle it as appropriate
        }
    }

    @PutMapping("/{id}")
    public ScoreCard updateScoreCard(@PathVariable Long id, @RequestBody ScoreCard updatedScoreCard) {
        try {
            // Fetch existing score card
            ScoreCard existingScoreCard = scoreCardService.getScoreCardById(id).orElse(null);

            if (existingScoreCard != null) {
                // Fetch team details based on team IDs
                Teams team1 = teamService.getTeamById(updatedScoreCard.getTeam1().getId());
                Teams team2 = teamService.getTeamById(updatedScoreCard.getTeam2().getId());

                if (team1 != null && team2 != null) {
                    // Set the fetched teams and other updated fields in the existing score card
                    existingScoreCard.setTeam1(team1);
                    existingScoreCard.setTeam2(team2);
                    existingScoreCard.setOrganization(updatedScoreCard.getOrganization());
                    existingScoreCard.setEvent(updatedScoreCard.getEvent());
                    existingScoreCard.setMatchResult(updatedScoreCard.getMatchResult());
                    existingScoreCard.setTeam1Score(updatedScoreCard.getTeam1Score());
                    existingScoreCard.setTeam2Score(updatedScoreCard.getTeam2Score());
                    existingScoreCard.setMatchDetails(updatedScoreCard.getMatchDetails());

                    // Save the updated score card
                    ScoreCard savedScoreCard = scoreCardService.saveScoreCard(existingScoreCard);

                    // Notify score card update
                    notifyScoreCardUpdate(savedScoreCard);

                    return savedScoreCard;
                } else {
                    // Handle the case where team details are not found
                    throw new IllegalArgumentException("Invalid team IDs");
                }
            } else {
                // Handle the case where the score card with the given ID is not found
                throw new RuntimeException("Score card not found with ID: " + id);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error updating score card", e); // or handle it as appropriate
        }
    }

    private void notifyScoreCardUpdate(ScoreCard scoreCard) {
        try {
            messagingTemplate.convertAndSend("/topic/score-updates", scoreCard);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error notifying score card update", e); // or handle it as appropriate
        }
    }

    @DeleteMapping("/{id}")
    public void deleteScoreCard(@PathVariable Long id) {
        try {
            scoreCardService.deleteScoreCard(id);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Error deleting score card", e); // or handle it as appropriate
        }
    }
}

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
        return scoreCardService.getAllScoreCards();
    }

    @GetMapping("/{id}")
    public ScoreCard getScoreCardById(@PathVariable Long id) {
        return scoreCardService.getScoreCardById(id).orElse(null);
    }

    @PostMapping
    public ScoreCard saveScoreCard(@RequestBody ScoreCard scoreCard) {
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
            return null;
        }
    }

    @PutMapping("/{id}")
    public ScoreCard updateScoreCard(@PathVariable Long id, @RequestBody ScoreCard updatedScoreCard) {
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
                return null;
            }
        } else {
            // Handle the case where the score card with the given ID is not found
            return null;
        }
    }

    private void notifyScoreCardUpdate(ScoreCard scoreCard) {
        messagingTemplate.convertAndSend("/topic/score-updates", scoreCard);
    }

    @DeleteMapping("/{id}")
    public void deleteScoreCard(@PathVariable Long id) {
        scoreCardService.deleteScoreCard(id);
    }
}

package com.sportapi.controllers;

import com.sportapi.model.DTO.LeaderboardDto;
import com.sportapi.model.Leaderboard;
import com.sportapi.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leaderboards")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping
    public ResponseEntity<List<Leaderboard>> getAllLeaderboards() {
        return ResponseEntity.ok(leaderboardService.getAllLeaderboards());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Leaderboard> getLeaderboardByEventId(@PathVariable Long eventId) {
        return ResponseEntity.ok(leaderboardService.getLeaderboardByEventId(eventId));
    }

    @PostMapping
    public ResponseEntity<Leaderboard> createOrUpdateLeaderboard(@Valid @RequestBody LeaderboardDto leaderboardDto) {
        return ResponseEntity.ok(leaderboardService.createOrUpdateLeaderboard(leaderboardDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaderboard(@PathVariable Long id) {
        leaderboardService.deleteLeaderboard(id);
        return ResponseEntity.noContent().build(); // Return HTTP 204 No Content
    }
}

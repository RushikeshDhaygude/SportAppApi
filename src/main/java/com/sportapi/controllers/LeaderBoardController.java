package com.sportapi.controllers;

import com.sportapi.model.DTO.AddLeaderboardRecordsDTO;
import com.sportapi.model.DTO.LeaderboardDTO;
import com.sportapi.services.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderBoardController {
    @Autowired
    private LeaderBoardService leaderboardService;

    @GetMapping
    public List<LeaderboardDTO> getAllLeaderboardRecords() {
        return leaderboardService.getAllLeaderboardRecords();
    }

    @GetMapping("/{eventId}")
    public List<LeaderboardDTO> getLeaderboardByEvent(@PathVariable Long eventId) {
        return leaderboardService.getLeaderboardByEvent(eventId);
    }

    @PostMapping
    public void addLeaderboardRecords(@RequestBody List<AddLeaderboardRecordsDTO> recordsDTO) {
        leaderboardService.addLeaderboardRecords(recordsDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLeaderboardRecord(@PathVariable Long id) {
        leaderboardService.deleteLeaderboardRecord(id);
    }

    @PutMapping("/{id}")
    public LeaderboardDTO updateLeaderboardRecord(@PathVariable Long id, @RequestBody AddLeaderboardRecordsDTO updateDTO) {
        return leaderboardService.updateLeaderboardRecord(id, updateDTO);
    }
}

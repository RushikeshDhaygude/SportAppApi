package com.sportapi.services;

import com.sportapi.model.DTO.LeaderboardDto;
import com.sportapi.model.Leaderboard;

import java.util.List;

public interface LeaderboardService {
    List<Leaderboard> getAllLeaderboards();
    Leaderboard getLeaderboardByEventId(Long eventId);
    Leaderboard createOrUpdateLeaderboard(LeaderboardDto leaderboardDto);
    void deleteLeaderboard(Long id); // New method for deletion
}

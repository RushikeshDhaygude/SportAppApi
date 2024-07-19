package com.sportapi.services.Impl;

import com.sportapi.model.DTO.LeaderboardDto;
import com.sportapi.model.Event;
import com.sportapi.model.Leaderboard;
import com.sportapi.model.TeamScore;
import com.sportapi.repositories.EventRepository;
import com.sportapi.repositories.LeaderboardRepository;
import com.sportapi.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Leaderboard> getAllLeaderboards() {
        return leaderboardRepository.findAll();
    }

    @Override
    public Leaderboard getLeaderboardByEventId(Long eventId) {
        return leaderboardRepository.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Leaderboard not found for event ID: " + eventId));
    }

    @Override
    @Transactional
    public Leaderboard createOrUpdateLeaderboard(LeaderboardDto leaderboardDto) {
        Event event = eventRepository.findById(leaderboardDto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Leaderboard leaderboard = leaderboardRepository.findByEventId(event.getId())
                .orElse(new Leaderboard());
        leaderboard.setEvent(event);

        if (leaderboard.getTeamScores() == null) {
            leaderboard.setTeamScores(new ArrayList<>());
        } else {
            leaderboard.getTeamScores().clear();
        }

        List<TeamScore> teamScores = leaderboardDto.getTeamScores().stream().map(dto -> {
            TeamScore teamScore = new TeamScore();
            teamScore.setTeamId(dto.getTeamId());
            teamScore.setMatchesPlayed(dto.getMatchesPlayed());
            teamScore.setMatchesWon(dto.getMatchesWon());
            teamScore.setMatchesLost(dto.getMatchesLost());
            teamScore.setTotalPoints(dto.getTotalPoints());
            teamScore.setLeaderboard(leaderboard);
            return teamScore;
        }).collect(Collectors.toList());

        leaderboard.getTeamScores().addAll(teamScores);

        return leaderboardRepository.save(leaderboard);
    }

    @Override
    @Transactional
    public void deleteLeaderboard(Long id) {
        Leaderboard leaderboard = leaderboardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leaderboard not found for ID: " + id));
        leaderboardRepository.delete(leaderboard);
    }
}

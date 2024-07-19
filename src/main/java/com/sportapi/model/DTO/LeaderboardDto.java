package com.sportapi.model.DTO;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class LeaderboardDto {
    @NotNull
    private Long eventId;

    @NotNull
    private List<TeamScoreDto> teamScores;

    // getters and setters
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<TeamScoreDto> getTeamScores() {
        return teamScores;
    }

    public void setTeamScores(List<TeamScoreDto> teamScores) {
        this.teamScores = teamScores;
    }
}

package com.sportapi.model.DTO;


import lombok.Getter;

@Getter
public class AddLeaderboardRecordsDTO {
    private Long eventId;
    private Long teamId;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int totalPoints;


    public AddLeaderboardRecordsDTO(Long eventId, Long teamId, int matchesPlayed, int matchesWon, int matchesLost, int totalPoints) {
        this.eventId = eventId;
        this.teamId = teamId;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;
        this.totalPoints = totalPoints;
    }

}

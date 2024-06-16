package com.sportapi.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LeaderboardDTO {
    private Long eventId;
    private Long teamId;
    private String teamName;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int totalPoints;

}


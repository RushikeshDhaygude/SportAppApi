package com.sportapi.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ScoreCardRequest {
    private Long team1Id;
    private Long team2Id;
    private String matchResult;
    private String team1Score;
    private String team2Score;
    private String matchDetails;
    private String status;
    private Long eventId;
    private String eventLocation;
    private LocalDate eventDate;

    // Getters and setters
}

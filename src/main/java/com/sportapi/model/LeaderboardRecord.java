package com.sportapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leaderboard_records")
public class LeaderboardRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Long teamId;

    @Column(nullable = false)
    private int matchesPlayed;

    @Column(nullable = false)
    private int matchesWon;

    @Column(nullable = false)
    private int matchesLost;

    @Column(nullable = false)
    private int totalPoints;

    // Constructors, getters, and setters

    public LeaderboardRecord() {
        // Default constructor
    }

    public LeaderboardRecord(Long eventId, Long teamId, int matchesPlayed, int matchesWon, int matchesLost, int totalPoints) {
        this.eventId = eventId;
        this.teamId = teamId;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;
        this.totalPoints = totalPoints;
    }

    // Getters and setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}

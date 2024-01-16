package com.sportapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leaderboards")
public class LeaderBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Teams team;

    @Column(nullable = false)
    private int matchesPlayed;

    @Column(nullable = false)
    private int won;

    @Column(nullable = false)
    private int lost;

    @Column(nullable = false)
    private int totalPoints;

    // Other columns as needed

    // Constructors, getters, and setters

    public LeaderBoard() {
        // Default constructor
    }

    public LeaderBoard(Event event, Teams team, int matchesPlayed, int won, int lost, int totalPoints) {
        this.event = event;
        this.team = team;
        this.matchesPlayed = matchesPlayed;
        this.won = won;
        this.lost = lost;
        this.totalPoints = totalPoints;
    }

    // Getters and setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

}

package com.sportapi.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "leaderboard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamScore> teamScores = new ArrayList<>();

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

    public List<TeamScore> getTeamScores() {
        return teamScores;
    }

    public void setTeamScores(List<TeamScore> teamScores) {
        this.teamScores = teamScores;
    }
}

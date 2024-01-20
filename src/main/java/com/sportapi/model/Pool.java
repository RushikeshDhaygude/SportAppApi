package com.sportapi.model;

import com.sportapi.model.Event;
import com.sportapi.model.Teams;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String poolName;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pool_id")
    private List<Teams> teams;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Teams> getTeams() {
        return teams;
    }

    public void setTeams(List<Teams> teams) {
        this.teams = teams;
    }
}

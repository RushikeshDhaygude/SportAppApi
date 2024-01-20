package com.sportapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fixtures")
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fixtureId;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    @JsonIgnoreProperties("fixtures") // Ignore the 'fixtures' property in Teams class during serialization
    private Teams team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    @JsonIgnoreProperties("fixtures") // Ignore the 'fixtures' property in Teams class during serialization
    private Teams team2;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String gender;

    public  Fixture()
    {

    }
//    public Fixture(Long fixtureId, Event event, Teams team1, Teams team2, LocalDateTime dateTime, String gender) {
//        this.fixtureId = fixtureId;
//        this.event = event;
//        this.team1 = team1;
//        this.team2 = team2;
//        this.dateTime = dateTime;
//        this.gender = gender;
//    }

    public Fixture(Event event, Teams team1, Teams team2, LocalDateTime dateTime, String gender) {
        this.event = event;
        this.team1 = team1;
        this.team2 = team2;
        this.dateTime = dateTime;
        this.gender = gender;
    }

    public Long getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(Long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Teams getTeam1() {
        return team1;
    }

    public void setTeam1(Teams team1) {
        this.team1 = team1;
    }

    public Teams getTeam2() {
        return team2;
    }

    public void setTeam2(Teams team2) {
        this.team2 = team2;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


// Other methods...
}

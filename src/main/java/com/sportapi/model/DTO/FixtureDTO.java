package com.sportapi.model.DTO;

import java.time.LocalDateTime;

public class FixtureDTO {

    private Long fixtureId;
    private Long eventId;
    private Long team1Id;
    private Long team2Id;
    private LocalDateTime dateTime;
    private String gender;

    // Constructors, getters, and setters

    // Constructor for creating DTO from Fixture entity
    public FixtureDTO(Long fixtureId, Long eventId, Long team1Id, Long team2Id, LocalDateTime dateTime, String gender) {
        this.fixtureId = fixtureId;
        this.eventId = eventId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.dateTime = dateTime;
        this.gender = gender;
    }

    public FixtureDTO() {

    }

    public Long getId() {
        return fixtureId;
    }

    public void setId(Long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(Long team1Id) {
        this.team1Id = team1Id;
    }

    public Long getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(Long team2Id) {
        this.team2Id = team2Id;
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
}

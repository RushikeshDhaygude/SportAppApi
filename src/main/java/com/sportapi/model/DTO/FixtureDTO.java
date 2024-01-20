package com.sportapi.model.DTO;

import java.time.LocalDateTime;

public class FixtureDTO {
    private Long eventId;
    private Long team1Id;
    private Long team2Id;
    private LocalDateTime dateTime;
    private String gender;

    public FixtureDTO() {
    }

    public FixtureDTO(Long eventId, Long team1Id, Long team2Id, LocalDateTime dateTime, String gender) {
        this.eventId = eventId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.dateTime = dateTime;
        this.gender = gender;
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

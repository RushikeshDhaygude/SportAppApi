package com.sportapi.model.DTO;


public class TeamDTO {

    private String teamName;
    private String teamLogoPath;
    private String teamCaptain;
    private String teamCaptainContact;
    private Long eventId;

    // Getters and setters

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLogoPath() {
        return teamLogoPath;
    }

    public void setTeamLogoPath(String teamLogoPath) {
        this.teamLogoPath = teamLogoPath;
    }

    public String getTeamCaptain() {
        return teamCaptain;
    }

    public void setTeamCaptain(String teamCaptain) {
        this.teamCaptain = teamCaptain;
    }

    public String getTeamCaptainContact() {
        return teamCaptainContact;
    }

    public void setTeamCaptainContact(String teamCaptainContact) {
        this.teamCaptainContact = teamCaptainContact;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}

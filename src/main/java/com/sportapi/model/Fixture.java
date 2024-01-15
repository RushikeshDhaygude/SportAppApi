package com.sportapi.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fixtures")
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fixture_id;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Teams team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Teams team2;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String gender; // You can replace this with an Enum if applicable

    // Other columns as needed

    // Constructors, getters, and setters

    public Fixture() {
        // Default constructor
    }

    public Fixture(Organization organization, Event event, Teams team1, Teams team2, LocalDateTime dateTime, String gender) {
        this.organization = organization;
        this.event = event;
        this.team1 = team1;
        this.team2 = team2;
        this.dateTime = dateTime;
        this.gender = gender;
    }

    // Getters and setters for all fields

    public Long getId() {
        return fixture_id;
    }

    public void setId(Long fixture_id) {
        this.fixture_id = fixture_id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
}

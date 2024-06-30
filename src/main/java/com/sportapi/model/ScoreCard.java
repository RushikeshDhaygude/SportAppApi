//package com.sportapi.model;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonInclude.Include;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "scorecards")
//public class ScoreCard {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "team1_id", nullable = false)
//    private Teams team1;
//
//    @ManyToOne
//    @JoinColumn(name = "team2_id", nullable = false)
//    private Teams team2;
//
//    @Column(nullable = false)
//    private String matchResult;
//
//    @Column(nullable = false)
//    private String team1Score;
//
//    @Column(nullable = false)
//    private String team2Score;
//
//    @ManyToOne
//    @JoinColumn(name = "organization_id", nullable = true)
//    @JsonInclude(Include.NON_NULL)
//    private Organization organization;
//
//    @ManyToOne
//    @JoinColumn(name = "event_id", nullable = false)
//    @JsonInclude(Include.NON_NULL)
//    private Event event;
//
//    @Column
//    private String matchDetails;
//
//    // Constructors, getters, and setters
//
//    public ScoreCard() {
//        // Default constructor
//    }
//
//    // Getters and setters for all fields
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Teams getTeam1() {
//        return team1;
//    }
//
//    public void setTeam1(Teams team1) {
//        this.team1 = team1;
//    }
//
//    public Teams getTeam2() {
//        return team2;
//    }
//
//    public void setTeam2(Teams team2) {
//        this.team2 = team2;
//    }
//
//    public String getMatchResult() {
//        return matchResult;
//    }
//
//    public void setMatchResult(String matchResult) {
//        this.matchResult = matchResult;
//    }
//
//    public String getTeam1Score() {
//        return team1Score;
//    }
//
//    public void setTeam1Score(String team1Score) {
//        this.team1Score = team1Score;
//    }
//
//    public String getTeam2Score() {
//        return team2Score;
//    }
//
//    public void setTeam2Score(String team2Score) {
//        this.team2Score = team2Score;
//    }
//
//    public Organization getOrganization() {
//        return organization;
//    }
//
//    public void setOrganization(Organization organization) {
//        this.organization = organization;
//    }
//
//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }
//
//    public String getMatchDetails() {
//        return matchDetails;
//    }
//
//    public void setMatchDetails(String matchDetails) {
//        this.matchDetails = matchDetails;
//    }
//}
package com.sportapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ScoreCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team1_id", nullable = false)
    private Teams team1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team2_id", nullable = false)
    private Teams team2;

    @Column(nullable = false)
    private String matchResult;

    @Column(nullable = false)
    private String team1Score;

    @Column(nullable = false)
    private String team2Score;

    @Column(nullable = false)
    private String matchDetails;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column
    private String eventLocation;

    @Column
    private LocalDateTime eventDate;

    // Getters and setters for all fields
}

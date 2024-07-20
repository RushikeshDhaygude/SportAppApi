//package com.sportapi.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//
//import jakarta.persistence.*;
//
//@Entity
//public class TeamScore {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Long teamId;
//    private Integer matchesPlayed;
//    private Integer matchesWon;
//    private Integer matchesLost;
//    private Integer totalPoints;
//
//    @ManyToOne
//    @JoinColumn(name = "leaderboard_id")
//    @JsonBackReference
//    private Leaderboard leaderboard;
//
//    // getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }
//
//    public Integer getMatchesPlayed() {
//        return matchesPlayed;
//    }
//
//    public void setMatchesPlayed(Integer matchesPlayed) {
//        this.matchesPlayed = matchesPlayed;
//    }
//
//    public Integer getMatchesWon() {
//        return matchesWon;
//    }
//
//    public void setMatchesWon(Integer matchesWon) {
//        this.matchesWon = matchesWon;
//    }
//
//    public Integer getMatchesLost() {
//        return matchesLost;
//    }
//
//    public void setMatchesLost(Integer matchesLost) {
//        this.matchesLost = matchesLost;
//    }
//
//    public Integer getTotalPoints() {
//        return totalPoints;
//    }
//
//    public void setTotalPoints(Integer totalPoints) {
//        this.totalPoints = totalPoints;
//    }
//
//    public Leaderboard getLeaderboard() {
//        return leaderboard;
//    }
//
//    public void setLeaderboard(Leaderboard leaderboard) {
//        this.leaderboard = leaderboard;
//    }
//}
package com.sportapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class TeamScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teamId;
    private String teamName; // Add teamName
    private String teamLogoPath; // Add teamLogoPath

    private Integer matchesPlayed;
    private Integer matchesWon;
    private Integer matchesLost;
    private Integer totalPoints;

    @ManyToOne
    @JoinColumn(name = "leaderboard_id")
    @JsonBackReference
    private Leaderboard leaderboard;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

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

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Integer getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(Integer matchesWon) {
        this.matchesWon = matchesWon;
    }

    public Integer getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(Integer matchesLost) {
        this.matchesLost = matchesLost;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }
}

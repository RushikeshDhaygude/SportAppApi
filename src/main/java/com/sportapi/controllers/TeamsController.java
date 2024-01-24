package com.sportapi.controllers;

import com.sportapi.model.Teams;
import com.sportapi.services.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    private final TeamsService teamsService;

    @Autowired
    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @GetMapping
    public ResponseEntity<List<Teams>> getAllTeams() {
        try {
            List<Teams> teams = teamsService.getAllTeams();
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teams> getTeamById(@PathVariable Long id) {
        try {
            Teams team = teamsService.getTeamById(id);
            if (team != null) {
                return new ResponseEntity<>(team, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> createTeam(
            @RequestParam("teamName") String teamName,
            @RequestParam("teamLogo") MultipartFile teamLogo,
            @RequestParam("teamCaptain") String teamCaptain,
            @RequestParam("teamCaptainContact") String teamCaptainContact) {
        try {
            teamsService.createTeam(teamName, teamLogo, teamCaptain, teamCaptainContact);
            return new ResponseEntity<>("Team created successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>("Error creating team", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTeam(
            @PathVariable Long id,
            @RequestParam("teamName") String teamName,
            @RequestParam(value = "teamLogo", required = false) MultipartFile teamLogo,
            @RequestParam("teamCaptain") String teamCaptain,
            @RequestParam("teamCaptainContact") String teamCaptainContact) {
        try {
            teamsService.updateTeam(id, teamName, teamLogo, teamCaptain, teamCaptainContact);
            return new ResponseEntity<>("Team updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>("Error updating team", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        try {
            teamsService.deleteTeam(id);
            return new ResponseEntity<>("Team deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting team", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

//package com.sportapi.controllers;
//
//import com.sportapi.model.Teams;
//import com.sportapi.services.TeamsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/teams")
//public class TeamsController {
//
//    private final TeamsService teamsService;
//
//    @Autowired
//    public TeamsController(TeamsService teamsService) {
//        this.teamsService = teamsService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Teams>> getAllTeams() {
//        List<Teams> teams = teamsService.getAllTeams();
//        return new ResponseEntity<>(teams, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Teams> getTeamById(@PathVariable Long id) {
//        Teams team = teamsService.getTeamById(id);
//        if (team != null) {
//            return new ResponseEntity<>(team, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<String> createTeam(
//            @RequestParam("teamName") String teamName,
//            @RequestParam("teamLogo") MultipartFile teamLogo,
//            @RequestParam("teamCaptain") String teamCaptain,
//            @RequestParam("teamCaptainContact") String teamCaptainContact) {
//        teamsService.createTeam(teamName, teamLogo, teamCaptain, teamCaptainContact);
//        return new ResponseEntity<>("Team created successfully", HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateTeam(
//            @PathVariable Long id,
//            @RequestParam("teamName") String teamName,
//            @RequestParam(value = "teamLogo", required = false) MultipartFile teamLogo,
//            @RequestParam("teamCaptain") String teamCaptain,
//            @RequestParam("teamCaptainContact") String teamCaptainContact) {
//        teamsService.updateTeam(id, teamName, teamLogo, teamCaptain, teamCaptainContact);
//        return new ResponseEntity<>("Team updated successfully", HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
//        teamsService.deleteTeam(id);
//        return new ResponseEntity<>("Team deleted successfully", HttpStatus.OK);
//    }
//}
package com.sportapi.controllers;

import com.sportapi.model.Teams;
import com.sportapi.services.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    @Autowired
    private TeamsService teamsService;

    @GetMapping
    public ResponseEntity<List<Teams>> getAllTeams() {
        List<Teams> teams = teamsService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teams> getTeamById(@PathVariable Long id) {
        Teams team = teamsService.getTeamById(id);
        if (team != null) {
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createTeam(
            @RequestParam String teamName,
            @RequestParam(required = false) MultipartFile teamLogo,
            @RequestParam String teamCaptain,
            @RequestParam String teamCaptainContact,
            @RequestParam Long eventId) throws IOException {
        teamsService.createTeam(teamName, teamLogo, teamCaptain, teamCaptainContact, eventId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeam(
            @PathVariable Long id,
            @RequestParam String teamName,
            @RequestParam(required = false) MultipartFile teamLogo,
            @RequestParam String teamCaptain,
            @RequestParam String teamCaptainContact,
            @RequestParam Long eventId) throws IOException {
        teamsService.updateTeam(id, teamName, teamLogo, teamCaptain, teamCaptainContact, eventId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamsService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }
}

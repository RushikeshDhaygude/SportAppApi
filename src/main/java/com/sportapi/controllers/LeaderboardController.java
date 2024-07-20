////package com.sportapi.controllers;
////
////import com.sportapi.model.DTO.LeaderboardDto;
////import com.sportapi.model.Leaderboard;
////import com.sportapi.services.LeaderboardService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////import jakarta.validation.Valid;
////import java.util.List;
////
////@RestController
////@RequestMapping("/leaderboards")
////public class LeaderboardController {
////
////    @Autowired
////    private LeaderboardService leaderboardService;
////
////    @GetMapping
////    public ResponseEntity<List<Leaderboard>> getAllLeaderboards() {
////        return ResponseEntity.ok(leaderboardService.getAllLeaderboards());
////    }
////
////    @GetMapping("/{eventId}")
////    public ResponseEntity<Leaderboard> getLeaderboardByEventId(@PathVariable Long eventId) {
////        return ResponseEntity.ok(leaderboardService.getLeaderboardByEventId(eventId));
////    }
////
////    @PostMapping
////    public ResponseEntity<Leaderboard> createOrUpdateLeaderboard(@Valid @RequestBody LeaderboardDto leaderboardDto) {
////        return ResponseEntity.ok(leaderboardService.createOrUpdateLeaderboard(leaderboardDto));
////    }
////
////    @DeleteMapping("/{id}")
////    public ResponseEntity<Void> deleteLeaderboard(@PathVariable Long id) {
////        leaderboardService.deleteLeaderboard(id);
////        return ResponseEntity.noContent().build(); // Return HTTP 204 No Content
////    }
////}
//package com.sportapi.controllers;
//
//import com.sportapi.model.DTO.LeaderboardDto;
//import com.sportapi.model.Leaderboard;
//import com.sportapi.services.LeaderboardService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/leaderboards")
//public class LeaderboardController {
//
//    @Autowired
//    private LeaderboardService leaderboardService;
//
//    @GetMapping
//    public ResponseEntity<List<Leaderboard>> getAllLeaderboards() {
//        List<Leaderboard> leaderboards = leaderboardService.getAllLeaderboards();
//        return ResponseEntity.ok(leaderboards);
//    }
//
//    @GetMapping("/{eventId}")
//    public ResponseEntity<Leaderboard> getLeaderboardByEventId(@PathVariable Long eventId) {
//        Leaderboard leaderboard = leaderboardService.getLeaderboardByEventId(eventId);
//        return ResponseEntity.ok(leaderboard);
//    }
//
//    @PostMapping
//    public ResponseEntity<Leaderboard> createOrUpdateLeaderboard(@RequestBody LeaderboardDto leaderboardDto) {
//        Leaderboard leaderboard = leaderboardService.createOrUpdateLeaderboard(leaderboardDto);
//        return ResponseEntity.ok(leaderboard);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLeaderboard(@PathVariable Long id) {
//        leaderboardService.deleteLeaderboard(id);
//        return ResponseEntity.ok().build();
//    }
//}
package com.sportapi.controllers;

import com.sportapi.model.DTO.LeaderboardDto;
import com.sportapi.model.Leaderboard;
import com.sportapi.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboards")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping
    public ResponseEntity<List<Leaderboard>> getAllLeaderboards() {
        List<Leaderboard> leaderboards = leaderboardService.getAllLeaderboards();
        return ResponseEntity.ok(leaderboards);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Leaderboard> getLeaderboardByEventId(@PathVariable Long eventId) {
        Leaderboard leaderboard = leaderboardService.getLeaderboardByEventId(eventId);
        return ResponseEntity.ok(leaderboard);
    }

    @PostMapping
    public ResponseEntity<?> createOrUpdateLeaderboard(@RequestBody LeaderboardDto leaderboardDto) {
        try {
            Leaderboard leaderboard = leaderboardService.createOrUpdateLeaderboard(leaderboardDto);
            return ResponseEntity.ok(leaderboard);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaderboard(@PathVariable Long id) {
        leaderboardService.deleteLeaderboard(id);
        return ResponseEntity.ok().build();
    }
}

package com.sportapi.controllers;

import com.sportapi.model.LeaderBoard;
import com.sportapi.services.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboards")
public class LeaderBoardController {

    @Autowired
    private LeaderBoardService leaderBoardService;

    @GetMapping
    public ResponseEntity<List<LeaderBoard>> getAllLeaderBoards() {
        try {
            List<LeaderBoard> leaderBoards = leaderBoardService.getLeaderBoards();
            return new ResponseEntity<>(leaderBoards, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaderBoard> getLeaderBoardById(@PathVariable Long id) {
        try {
            LeaderBoard leaderBoard = leaderBoardService.getLeaderBoardById(id);
            if (leaderBoard != null) {
                return new ResponseEntity<>(leaderBoard, HttpStatus.OK);
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
    public ResponseEntity<LeaderBoard> createLeaderBoard(@RequestBody LeaderBoard leaderBoard) {
        try {
            LeaderBoard createdLeaderBoard = leaderBoardService.createLeaderBoard(leaderBoard);
            return new ResponseEntity<>(createdLeaderBoard, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaderBoard> updateLeaderBoard(@PathVariable Long id, @RequestBody LeaderBoard leaderBoard) {
        try {
            LeaderBoard updatedLeaderBoard = leaderBoardService.updateLeaderBoard(id, leaderBoard);
            if (updatedLeaderBoard != null) {
                return new ResponseEntity<>(updatedLeaderBoard, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeaderBoard(@PathVariable Long id) {
        try {
            boolean deleted = leaderBoardService.deleteLeaderBoard(id);
            if (deleted) {
                return new ResponseEntity<>("LeaderBoard deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("LeaderBoard not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting LeaderBoard", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

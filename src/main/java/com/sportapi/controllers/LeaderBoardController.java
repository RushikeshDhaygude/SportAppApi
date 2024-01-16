package com.sportapi.controllers;

import com.sportapi.model.LeaderBoard;
import com.sportapi.services.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboards")
public class LeaderBoardController {

    @Autowired
    private LeaderBoardService leaderBoardService;

    @GetMapping
    public List<LeaderBoard> getAllLeaderBoards() {
        return leaderBoardService.getLeaderBoards();
    }

    @GetMapping("/{id}")
    public LeaderBoard getLeaderBoardById(@PathVariable Long id) {
        return leaderBoardService.getLeaderBoardById(id);
    }

    @PostMapping
    public LeaderBoard createLeaderBoard(@RequestBody LeaderBoard leaderBoard) {
        return leaderBoardService.createLeaderBoard(leaderBoard);
    }

    @PutMapping("/{id}")
    public LeaderBoard updateLeaderBoard(@PathVariable Long id, @RequestBody LeaderBoard leaderBoard) {
        return leaderBoardService.updateLeaderBoard(id, leaderBoard);
    }

    @DeleteMapping("/{id}")
    public boolean deleteLeaderBoard(@PathVariable Long id) {
        return leaderBoardService.deleteLeaderBoard(id);
    }
}

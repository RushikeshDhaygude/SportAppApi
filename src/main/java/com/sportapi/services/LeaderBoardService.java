package com.sportapi.services;

import com.sportapi.model.LeaderBoard;

import java.util.List;

public interface LeaderBoardService {

    List<LeaderBoard> getLeaderBoards();

    LeaderBoard getLeaderBoardById(Long id);

    LeaderBoard createLeaderBoard(LeaderBoard leaderBoard);

    LeaderBoard updateLeaderBoard(Long id, LeaderBoard leaderBoard);

    boolean deleteLeaderBoard(Long id);
}

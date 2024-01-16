package com.sportapi.services.Impl;

import com.sportapi.model.LeaderBoard;
import com.sportapi.repositories.LeaderBoardRepository;
import com.sportapi.services.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    @Autowired
    private LeaderBoardRepository leaderBoardRepository;

    @Override
    public List<LeaderBoard> getLeaderBoards() {
        // Use a custom query method with Sort parameter to fetch data in descending order of totalPoints
        return leaderBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "totalPoints"));
    }

    @Override
    public LeaderBoard getLeaderBoardById(Long id) {
        return leaderBoardRepository.findById(id).orElse(null);
    }

    @Override
    public LeaderBoard createLeaderBoard(LeaderBoard leaderBoard) {
        // You may want to perform additional validation or business logic here
        return leaderBoardRepository.save(leaderBoard);
    }

    @Override
    public LeaderBoard updateLeaderBoard(Long id, LeaderBoard leaderBoard) {
        if (leaderBoardRepository.existsById(id)) {
            leaderBoard.setId(id);
            return leaderBoardRepository.save(leaderBoard);
        } else {
            return null; // LeaderBoard with given id not found
        }
    }

    @Override
    public boolean deleteLeaderBoard(Long id) {
        if (leaderBoardRepository.existsById(id)) {
            leaderBoardRepository.deleteById(id);
            return true;
        } else {
            return false; // LeaderBoard with given id not found
        }
    }
}

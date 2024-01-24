package com.sportapi.services.Impl;

import com.sportapi.model.LeaderBoard;
import com.sportapi.repositories.LeaderBoardRepository;
import com.sportapi.services.LeaderBoardService;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    @Autowired
    private LeaderBoardRepository leaderBoardRepository;

    private static final Logger logger = LoggerFactory.getLogger(LeaderBoardServiceImpl.class);

    @Override
    public List<LeaderBoard> getLeaderBoards() {
        try {
            // Use a custom query method with Sort parameter to fetch data in descending order of totalPoints
            return leaderBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "totalPoints"));
        } catch (Exception e) {
            logger.error("Error fetching all leaderboards", e);
            throw new ServiceException("Error fetching all leaderboards", e);
        }
    }

    @Override
    public LeaderBoard getLeaderBoardById(Long id) {
        try {
            return leaderBoardRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Error fetching leaderboard by ID: " + id, e);
            throw new ServiceException("Error fetching leaderboard by ID: " + id, e);
        }
    }

    @Override
    public LeaderBoard createLeaderBoard(LeaderBoard leaderBoard) {
        try {
            // You may want to perform additional validation or business logic here
            return leaderBoardRepository.save(leaderBoard);
        } catch (Exception e) {
            logger.error("Error creating leaderboard", e);
            throw new ServiceException("Error creating leaderboard", e);
        }
    }

    @Override
    public LeaderBoard updateLeaderBoard(Long id, LeaderBoard leaderBoard) {
        try {
            if (leaderBoardRepository.existsById(id)) {
                leaderBoard.setId(id);
                return leaderBoardRepository.save(leaderBoard);
            } else {
                return null; // LeaderBoard with given id not found
            }
        } catch (Exception e) {
            logger.error("Error updating leaderboard with ID: " + id, e);
            throw new ServiceException("Error updating leaderboard with ID: " + id, e);
        }
    }

    @Override
    public boolean deleteLeaderBoard(Long id) {
        try {
            if (leaderBoardRepository.existsById(id)) {
                leaderBoardRepository.deleteById(id);
                return true;
            } else {
                return false; // LeaderBoard with given id not found
            }
        } catch (Exception e) {
            logger.error("Error deleting leaderboard with ID: " + id, e);
            throw new ServiceException("Error deleting leaderboard with ID: " + id, e);
        }
    }
}

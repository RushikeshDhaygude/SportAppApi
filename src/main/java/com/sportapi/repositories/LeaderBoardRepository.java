package com.sportapi.repositories;

import com.sportapi.model.LeaderBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Long> {

    // This method will automatically generate a query to fetch all leaderboards
    // and order them by total points in descending order
//    List<LeaderBoard> findAllOrderByTotalPointsDesc();
    List<LeaderBoard> findAllByOrderByTotalPointsDesc();
}

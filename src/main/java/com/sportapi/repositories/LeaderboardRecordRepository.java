package com.sportapi.repositories;

import com.sportapi.model.LeaderboardRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaderboardRecordRepository extends JpaRepository<LeaderboardRecord, Long> {
    List<LeaderboardRecord> findByEventId(Long eventId);
    List<LeaderboardRecord> findByTeamId(Long teamId);
    List<LeaderboardRecord> findByEventIdAndTeamId(Long eventId, Long teamId);
}

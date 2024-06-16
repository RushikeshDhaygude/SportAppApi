package com.sportapi.services;

import com.sportapi.model.DTO.AddLeaderboardRecordsDTO;
import com.sportapi.model.DTO.LeaderboardDTO;
import com.sportapi.model.LeaderboardRecord;

import java.util.List;

public interface LeaderBoardService {
    

    List<LeaderboardDTO> getAllLeaderboardRecords();

    List<LeaderboardDTO> getLeaderboardByEvent(Long eventId);

    void addLeaderboardRecords(List<AddLeaderboardRecordsDTO> recordsDTO);


    void deleteLeaderboardRecord(Long id);


    LeaderboardDTO updateLeaderboardRecord(Long id, AddLeaderboardRecordsDTO updateDTO);
}

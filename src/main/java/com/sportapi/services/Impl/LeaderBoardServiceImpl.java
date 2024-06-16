package com.sportapi.services.Impl;

import com.sportapi.model.DTO.AddLeaderboardRecordsDTO;
import com.sportapi.model.DTO.LeaderboardDTO;
import com.sportapi.model.LeaderboardRecord;
import com.sportapi.model.Teams;
import com.sportapi.repositories.LeaderboardRecordRepository;
import com.sportapi.repositories.TeamsRepository;
import com.sportapi.services.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {
    @Autowired
    private LeaderboardRecordRepository leaderboardRecordRepository;

    @Autowired
    private TeamsRepository teamsRepository;

    public List<LeaderboardDTO> getAllLeaderboardRecords() {
        List<LeaderboardRecord> records = leaderboardRecordRepository.findAll();
        return records.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<LeaderboardDTO> getLeaderboardByEvent(Long eventId) {
        List<LeaderboardRecord> records = leaderboardRecordRepository.findByEventId(eventId);
        return records.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void addLeaderboardRecords(List<AddLeaderboardRecordsDTO> recordsDTO) {
        List<LeaderboardRecord> records = recordsDTO.stream().map(this::convertToEntity).collect(Collectors.toList());
        leaderboardRecordRepository.saveAll(records);
    }

    public void deleteLeaderboardRecord(Long id) {
        leaderboardRecordRepository.deleteById(id);
    }

    public LeaderboardDTO updateLeaderboardRecord(Long id, AddLeaderboardRecordsDTO updateDTO) {
        Optional<LeaderboardRecord> existingRecordOpt = leaderboardRecordRepository.findById(id);
        if (existingRecordOpt.isPresent()) {
            LeaderboardRecord existingRecord = existingRecordOpt.get();
            existingRecord.setMatchesPlayed(updateDTO.getMatchesPlayed());
            existingRecord.setMatchesWon(updateDTO.getMatchesWon());
            existingRecord.setMatchesLost(updateDTO.getMatchesLost());
            existingRecord.setTotalPoints(updateDTO.getTotalPoints());
            leaderboardRecordRepository.save(existingRecord);
            return convertToDTO(existingRecord);
        } else {
            throw new RuntimeException("Record not found with ID: " + id);
        }
    }

    private LeaderboardDTO convertToDTO(LeaderboardRecord record) {
        Teams team = teamsRepository.findById(record.getTeamId()).orElseThrow();
        LeaderboardDTO dto = new LeaderboardDTO();
        dto.setEventId(record.getEventId());
        dto.setTeamId(record.getTeamId());
        dto.setTeamName(team.getTeamName());
        dto.setMatchesPlayed(record.getMatchesPlayed());
        dto.setMatchesWon(record.getMatchesWon());
        dto.setMatchesLost(record.getMatchesLost());
        dto.setTotalPoints(record.getTotalPoints());
        return dto;
    }

    private LeaderboardRecord convertToEntity(AddLeaderboardRecordsDTO dto) {
        return new LeaderboardRecord(dto.getEventId(), dto.getTeamId(), dto.getMatchesPlayed(), dto.getMatchesWon(), dto.getMatchesLost(), dto.getTotalPoints());
    }
}

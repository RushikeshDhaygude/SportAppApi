package com.sportapi.services.Impl;

import com.sportapi.model.Teams;
import com.sportapi.repositories.TeamsRepository;
import com.sportapi.services.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService {

    private final TeamsRepository teamsRepository;
    private final FileUploadService fileUploadService;

    @Autowired
    public TeamsServiceImpl(TeamsRepository teamsRepository, FileUploadService fileUploadService) {
        this.teamsRepository = teamsRepository;
        this.fileUploadService = fileUploadService;
    }

    @Override
    public List<Teams> getAllTeams() {
        return teamsRepository.findAll();
    }

    @Override
    public Teams getTeamById(Long id) {
        return teamsRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void createTeam(String teamName, MultipartFile teamLogo, String teamCaptain, String teamCaptainContact) {
        Teams team = new Teams();
        team.setTeamName(teamName);
        team.setTeamCaptain(teamCaptain);
        team.setTeamCaptainContact(teamCaptainContact);

        if (teamLogo != null && !teamLogo.isEmpty()) {
            String logoPath = fileUploadService.uploadFile(teamLogo, "images/team-logos");
            team.setTeamLogoPath(logoPath);
        }

        teamsRepository.save(team);
    }

    @Override
    @Transactional
    public void updateTeam(Long id, String teamName, MultipartFile teamLogo, String teamCaptain, String teamCaptainContact) {
        Teams existingTeam = teamsRepository.findById(id).orElse(null);

        if (existingTeam != null) {
            existingTeam.setTeamName(teamName);
            existingTeam.setTeamCaptain(teamCaptain);
            existingTeam.setTeamCaptainContact(teamCaptainContact);

            if (teamLogo != null && !teamLogo.isEmpty()) {
                String logoPath = fileUploadService.uploadFile(teamLogo, "images/team-logos");
                existingTeam.setTeamLogoPath(logoPath);
            }

            teamsRepository.save(existingTeam);
        }
    }

    @Override
    @Transactional
    public void deleteTeam(Long id) {
        teamsRepository.deleteById(id);
    }
}

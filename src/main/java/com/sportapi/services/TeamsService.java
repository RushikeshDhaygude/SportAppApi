//package com.sportapi.services;
//
//import com.sportapi.model.Teams;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//public interface TeamsService {
//
//    List<Teams> getAllTeams();
//
//    Teams getTeamById(Long id);
//
//    void createTeam(String teamName, MultipartFile teamLogo, String teamCaptain, String teamCaptainContact);
//
//    void updateTeam(Long id, String teamName, MultipartFile teamLogo, String teamCaptain, String teamCaptainContact);
//
//    void deleteTeam(Long id);
//}
package com.sportapi.services;

import com.sportapi.model.Teams;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeamsService {
    List<Teams> getAllTeams();
    Teams getTeamById(Long id);
    void createTeam(String teamName, MultipartFile teamLogo, String teamCaptain, String teamCaptainContact, Long eventId);
    void updateTeam(Long id, String teamName, MultipartFile teamLogo, String teamCaptain, String teamCaptainContact, Long eventId);
    void deleteTeam(Long id);
}

//package com.sportapi.controllers;
//
//import com.sportapi.model.ScoreCard;
//import com.sportapi.model.Teams;
//import com.sportapi.services.ScoreCardService;
//import com.sportapi.services.TeamsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/scorecards")
//public class ScoreCardController {
//
//    @Autowired
//    private ScoreCardService scoreCardService;
//
//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Autowired
//    private TeamsService teamService;
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    @GetMapping
//    public List<ScoreCard> getAllScoreCards() {
//        return scoreCardService.getAllScoreCards();
//    }
//
//    @GetMapping("/{id}")
//    public ScoreCard getScoreCardById(@PathVariable Long id) {
//        return scoreCardService.getScoreCardById(id).orElse(null);
//    }
//
////    @PostMapping
////    public ScoreCard saveScoreCard(@RequestBody ScoreCard scoreCard) {
////        // Fetch team details based on team IDs
////        Teams team1 = teamService.getTeamById(scoreCard.getTeam1().getId());
////        Teams team2 = teamService.getTeamById(scoreCard.getTeam2().getId());
////
////        if (team1 != null && team2 != null) {
////            // Set the fetched teams in the score card
////            scoreCard.setTeam1(team1);
////            scoreCard.setTeam2(team2);
////
////            // Additional logic if needed
////            ScoreCard savedScoreCard = scoreCardService.saveScoreCard(scoreCard);
////            notifyScoreCardUpdate(savedScoreCard);
////            return savedScoreCard;
////        } else {
////            // Handle the case where team details are not found
////            return null;
////        }
////    }
////
////    @PutMapping("/{id}")
////    public ScoreCard updateScoreCard(@PathVariable Long id, @RequestBody ScoreCard updatedScoreCard) {
////        // Fetch existing score card
////        ScoreCard existingScoreCard = scoreCardService.getScoreCardById(id).orElse(null);
////
////        if (existingScoreCard != null) {
////            // Fetch team details based on team IDs
////            Teams team1 = teamService.getTeamById(updatedScoreCard.getTeam1().getId());
////            Teams team2 = teamService.getTeamById(updatedScoreCard.getTeam2().getId());
////
////            if (team1 != null && team2 != null) {
////                // Set the fetched teams and other updated fields in the existing score card
////                existingScoreCard.setTeam1(team1);
////                existingScoreCard.setTeam2(team2);
////                existingScoreCard.setOrganization(updatedScoreCard.getOrganization());
////                existingScoreCard.setEvent(updatedScoreCard.getEvent());
////                existingScoreCard.setMatchResult(updatedScoreCard.getMatchResult());
////                existingScoreCard.setTeam1Score(updatedScoreCard.getTeam1Score());
////                existingScoreCard.setTeam2Score(updatedScoreCard.getTeam2Score());
////                existingScoreCard.setMatchDetails(updatedScoreCard.getMatchDetails());
////
////                // Save the updated score card
////                ScoreCard savedScoreCard = scoreCardService.saveScoreCard(existingScoreCard);
////
////                // Notify score card update
////                notifyScoreCardUpdate(savedScoreCard);
////
////                return savedScoreCard;
////            } else {
////                // Handle the case where team details are not found
////                return null;
////            }
////        } else {
////            // Handle the case where the score card with the given ID is not found
////            return null;
////        }
////    }
//
//
//
////    private void notifyScoreCardUpdate(ScoreCard scoreCard) {
////        messagingTemplate.convertAndSend("/topic/score-updates", scoreCard);
////    }
//
//    @PostMapping
//    public ScoreCard saveScoreCard(@RequestBody ScoreCard scoreCard) {
//        Teams team1 = teamService.getTeamById(scoreCard.getTeam1().getId());
//        Teams team2 = teamService.getTeamById(scoreCard.getTeam2().getId());
//
//        if (team1 != null && team2 != null) {
//            scoreCard.setTeam1(team1);
//            scoreCard.setTeam2(team2);
//            ScoreCard savedScoreCard = scoreCardService.saveScoreCard(scoreCard);
//            notifyScoreCardUpdate(savedScoreCard);
//            return savedScoreCard;
//        } else {
//            return null;
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ScoreCard updateScoreCard(@PathVariable Long id, @RequestBody ScoreCard updatedScoreCard) {
//        ScoreCard existingScoreCard = scoreCardService.getScoreCardById(id).orElse(null);
//
//        if (existingScoreCard != null) {
//            Teams team1 = teamService.getTeamById(updatedScoreCard.getTeam1().getId());
//            Teams team2 = teamService.getTeamById(updatedScoreCard.getTeam2().getId());
//
//            if (team1 != null && team2 != null) {
//                existingScoreCard.setTeam1(team1);
//                existingScoreCard.setTeam2(team2);
//                existingScoreCard.setOrganization(updatedScoreCard.getOrganization());
//                existingScoreCard.setEvent(updatedScoreCard.getEvent());
//                existingScoreCard.setMatchResult(updatedScoreCard.getMatchResult());
//                existingScoreCard.setTeam1Score(updatedScoreCard.getTeam1Score());
//                existingScoreCard.setTeam2Score(updatedScoreCard.getTeam2Score());
//                existingScoreCard.setMatchDetails(updatedScoreCard.getMatchDetails());
//
//                ScoreCard savedScoreCard = scoreCardService.saveScoreCard(existingScoreCard);
//                notifyScoreCardUpdate(savedScoreCard);
//                return savedScoreCard;
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }
//
//    private void notifyScoreCardUpdate(ScoreCard scoreCard) {
//        kafkaTemplate.send("score-updates", scoreCard);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteScoreCard(@PathVariable Long id) {
//        scoreCardService.deleteScoreCard(id);
//    }
//}
//package com.sportapi.controllers;
//
//import com.sportapi.model.DTO.ScoreCardResponse;
//import com.sportapi.model.ScoreCard;
//import com.sportapi.model.Teams;
//import com.sportapi.services.ScoreCardService;
//import com.sportapi.services.TeamsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/scorecards")
//public class ScoreCardController {
//
//    @Autowired
//    private ScoreCardService scoreCardService;
//
//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Autowired
//    private TeamsService teamService;
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    @GetMapping
//    public List<ScoreCardResponse> getAllScoreCards() {
//        return scoreCardService.getAllScoreCards().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/{id}")
//    public ScoreCardResponse getScoreCardById(@PathVariable Long id) {
//        return scoreCardService.getScoreCardById(id)
//                .map(this::convertToDTO)
//                .orElse(null);
//    }
//
//    @PostMapping
//    public ScoreCardResponse saveScoreCard(@RequestBody ScoreCard scoreCard) {
//        Teams team1 = teamService.getTeamById(scoreCard.getTeam1().getId());
//        Teams team2 = teamService.getTeamById(scoreCard.getTeam2().getId());
//
//        if (team1 != null && team2 != null) {
//            scoreCard.setTeam1(team1);
//            scoreCard.setTeam2(team2);
//
//            ScoreCard savedScoreCard = scoreCardService.saveScoreCard(scoreCard);
//            if (!"FINAL".equals(scoreCard.getStatus())) {
//                notifyScoreCardUpdate(savedScoreCard);
//            }
//            return convertToDTO(savedScoreCard);
//        } else {
//            return null;
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ScoreCardResponse updateScoreCard(@PathVariable Long id, @RequestBody ScoreCard updatedScoreCard) {
//        ScoreCard existingScoreCard = scoreCardService.getScoreCardById(id).orElse(null);
//
//        if (existingScoreCard != null) {
//            Teams team1 = teamService.getTeamById(updatedScoreCard.getTeam1().getId());
//            Teams team2 = teamService.getTeamById(updatedScoreCard.getTeam2().getId());
//
//            if (team1 != null && team2 != null) {
//                existingScoreCard.setTeam1(team1);
//                existingScoreCard.setTeam2(team2);
//                existingScoreCard.setEvent(updatedScoreCard.getEvent());
//                existingScoreCard.setMatchResult(updatedScoreCard.getMatchResult());
//                existingScoreCard.setTeam1Score(updatedScoreCard.getTeam1Score());
//                existingScoreCard.setTeam2Score(updatedScoreCard.getTeam2Score());
//                existingScoreCard.setMatchDetails(updatedScoreCard.getMatchDetails());
//                existingScoreCard.setStatus(updatedScoreCard.getStatus());
//
//                existingScoreCard.setEventLocation(updatedScoreCard.getEventLocation());
//                existingScoreCard.setEventDate(updatedScoreCard.getEventDate());
//
//                ScoreCard savedScoreCard = scoreCardService.saveScoreCard(existingScoreCard);
//                if (!"FINAL".equals(existingScoreCard.getStatus())) {
//                    notifyScoreCardUpdate(savedScoreCard);
//                }
//                return convertToDTO(savedScoreCard);
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteScoreCard(@PathVariable Long id) {
//        scoreCardService.deleteScoreCard(id);
//    }
//
//    @GetMapping("/status/final")
//    public List<ScoreCardResponse> getFinalScoreCards() {
//        return scoreCardService.getFinalScoreCards().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/status/final/{eventId}")
//    public List<ScoreCardResponse> getFinalScoreCardsByEventId(@PathVariable Long eventId) {
//        return scoreCardService.getFinalScoreCardsByEventId(eventId).stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    private void notifyScoreCardUpdate(ScoreCard scoreCard) {
//        kafkaTemplate.send("score-updates", scoreCard);
//    }
//
//    private ScoreCardResponse convertToDTO(ScoreCard scoreCard) {
//        ScoreCardResponse dto = new ScoreCardResponse();
//        dto.setId(scoreCard.getId());
//
//        ScoreCardResponse.TeamResponse team1DTO = new ScoreCardResponse.TeamResponse();
//        team1DTO.setId(scoreCard.getTeam1().getId());
//        team1DTO.setTeamName(scoreCard.getTeam1().getTeamName());
//        team1DTO.setTeamLogoPath(scoreCard.getTeam1().getTeamLogoPath());
//        dto.setTeam1(team1DTO);
//
//        ScoreCardResponse.TeamResponse team2DTO = new ScoreCardResponse.TeamResponse();
//        team2DTO.setId(scoreCard.getTeam2().getId());
//        team2DTO.setTeamName(scoreCard.getTeam2().getTeamName());
//        team2DTO.setTeamLogoPath(scoreCard.getTeam2().getTeamLogoPath());
//        dto.setTeam2(team2DTO);
//
//        dto.setMatchResult(scoreCard.getMatchResult());
//        dto.setTeam1Score(scoreCard.getTeam1Score());
//        dto.setTeam2Score(scoreCard.getTeam2Score());
//        dto.setMatchDetails(scoreCard.getMatchDetails());
//        dto.setStatus(scoreCard.getStatus());
//
//        ScoreCardResponse.EventResponse eventDTO = new ScoreCardResponse.EventResponse();
//        eventDTO.setId(scoreCard.getEvent().getId());
//        dto.setEvent(eventDTO);
//
//        dto.setEventLocation(scoreCard.getEventLocation());
//        dto.setEventDate(scoreCard.getEventDate() != null ? scoreCard.getEventDate() : LocalDateTime.now());
//
//        return dto;
//    }
//}
package com.sportapi.controllers;

import com.sportapi.model.DTO.ScoreCardResponse;
import com.sportapi.model.Event;
import com.sportapi.model.ScoreCard;
import com.sportapi.model.Teams;
import com.sportapi.services.EventService;  // Add this import
import com.sportapi.services.ScoreCardService;
import com.sportapi.services.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/scorecards")
public class ScoreCardController {

    @Autowired
    private ScoreCardService scoreCardService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private TeamsService teamService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private EventService eventService;  // Add this field

    @GetMapping
    public List<ScoreCardResponse> getAllScoreCards() {
        return scoreCardService.getAllScoreCards().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ScoreCardResponse getScoreCardById(@PathVariable Long id) {
        return scoreCardService.getScoreCardById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @PostMapping
    public ScoreCardResponse saveScoreCard(@RequestBody ScoreCard scoreCard) {
        Teams team1 = teamService.getTeamById(scoreCard.getTeam1().getId());
        Teams team2 = teamService.getTeamById(scoreCard.getTeam2().getId());
        Event event = eventService.getEventById(scoreCard.getEvent().getId());  // Fetch the full event

        if (team1 != null && team2 != null && event != null) {
            scoreCard.setTeam1(team1);
            scoreCard.setTeam2(team2);
            scoreCard.setEvent(event);  // Set the fetched event

            ScoreCard savedScoreCard = scoreCardService.saveScoreCard(scoreCard);
            if (!"FINAL".equals(scoreCard.getStatus())) {
                notifyScoreCardUpdate(savedScoreCard);
            }
            return convertToDTO(savedScoreCard);
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ScoreCardResponse updateScoreCard(@PathVariable Long id, @RequestBody ScoreCard updatedScoreCard) {
        ScoreCard existingScoreCard = scoreCardService.getScoreCardById(id).orElse(null);

        if (existingScoreCard != null) {
            Teams team1 = teamService.getTeamById(updatedScoreCard.getTeam1().getId());
            Teams team2 = teamService.getTeamById(updatedScoreCard.getTeam2().getId());
            Event event = eventService.getEventById(updatedScoreCard.getEvent().getId());  // Fetch the full event

            if (team1 != null && team2 != null && event != null) {
                existingScoreCard.setTeam1(team1);
                existingScoreCard.setTeam2(team2);
                existingScoreCard.setEvent(event);  // Set the fetched event
                existingScoreCard.setMatchResult(updatedScoreCard.getMatchResult());
                existingScoreCard.setTeam1Score(updatedScoreCard.getTeam1Score());
                existingScoreCard.setTeam2Score(updatedScoreCard.getTeam2Score());
                existingScoreCard.setMatchDetails(updatedScoreCard.getMatchDetails());
                existingScoreCard.setStatus(updatedScoreCard.getStatus());
                existingScoreCard.setEventLocation(updatedScoreCard.getEventLocation());
                existingScoreCard.setEventDate(updatedScoreCard.getEventDate());

                ScoreCard savedScoreCard = scoreCardService.saveScoreCard(existingScoreCard);
//                if (!"FINAL".equals(existingScoreCard.getStatus())) {
                    notifyScoreCardUpdate(savedScoreCard);
//                }
                return convertToDTO(savedScoreCard);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteScoreCard(@PathVariable Long id) {
        scoreCardService.deleteScoreCard(id);
    }

    @GetMapping("/status/final")
    public List<ScoreCardResponse> getFinalScoreCards() {
        return scoreCardService.getFinalScoreCards().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/status/final/{eventId}")
    public List<ScoreCardResponse> getFinalScoreCardsByEventId(@PathVariable Long eventId) {
        return scoreCardService.getFinalScoreCardsByEventId(eventId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void notifyScoreCardUpdate(ScoreCard scoreCard) {
        kafkaTemplate.send("score-updates", scoreCard);
    }

    @Transactional
    private ScoreCardResponse convertToDTO(ScoreCard scoreCard) {
        ScoreCardResponse dto = new ScoreCardResponse();
        dto.setId(scoreCard.getId());

        ScoreCardResponse.TeamResponse team1DTO = new ScoreCardResponse.TeamResponse();
        team1DTO.setId(scoreCard.getTeam1().getId());
        team1DTO.setTeamName(scoreCard.getTeam1().getTeamName());
        team1DTO.setTeamLogoPath(scoreCard.getTeam1().getTeamLogoPath());
        dto.setTeam1(team1DTO);

        ScoreCardResponse.TeamResponse team2DTO = new ScoreCardResponse.TeamResponse();
        team2DTO.setId(scoreCard.getTeam2().getId());
        team2DTO.setTeamName(scoreCard.getTeam2().getTeamName());
        team2DTO.setTeamLogoPath(scoreCard.getTeam2().getTeamLogoPath());
        dto.setTeam2(team2DTO);

        dto.setMatchResult(scoreCard.getMatchResult());
        dto.setTeam1Score(scoreCard.getTeam1Score());
        dto.setTeam2Score(scoreCard.getTeam2Score());
        dto.setMatchDetails(scoreCard.getMatchDetails());
        dto.setStatus(scoreCard.getStatus());

        ScoreCardResponse.EventResponse eventDTO = new ScoreCardResponse.EventResponse();
        eventDTO.setId(scoreCard.getEvent().getId());
        eventDTO.setEventName(scoreCard.getEvent().getEventName());  // Set eventName
        dto.setEvent(eventDTO);

        dto.setEventLocation(scoreCard.getEventLocation());
        dto.setEventDate(scoreCard.getEventDate() != null ? scoreCard.getEventDate() : LocalDateTime.now());

        return dto;
    }
}

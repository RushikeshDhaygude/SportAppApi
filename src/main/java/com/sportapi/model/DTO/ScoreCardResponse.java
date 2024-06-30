//package com.sportapi.model.DTO;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//public class ScoreCardResponse {
//    private Long id;
//    private TeamResponse team1;
//    private TeamResponse team2;
//    private String matchResult;
//    private String team1Score;
//    private String team2Score;
//    private String matchDetails;
//    private String status;
//    private EventResponse event;
//    private String eventLocation;
//    private LocalDateTime eventDate;
//
//    @Getter
//    @Setter
//    public static class TeamResponse {
//        private Long id;
//        private String teamName;
//        private String teamLogoPath;
//
//        // Getters and setters
//    }
//
//    @Getter
//    @Setter
//    public static class EventResponse {
//        private Long id;
//
//        // Getters and setters
//    }
//
//    // Getters and setters for all fields
//}
package com.sportapi.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScoreCardResponse {
    private Long id;
    private TeamResponse team1;
    private TeamResponse team2;
    private String matchResult;
    private String team1Score;
    private String team2Score;
    private String matchDetails;
    private String status;
    private EventResponse event;
    private String eventLocation;
    private LocalDateTime eventDate;

    @Getter
    @Setter
    public static class TeamResponse {
        private Long id;
        private String teamName;
        private String teamLogoPath;

        // Getters and setters
    }

    @Getter
    @Setter
    public static class EventResponse {
        private Long id;
        private String eventName;  // Added eventName

        // Getters and setters
    }

    // Getters and setters for all fields
}

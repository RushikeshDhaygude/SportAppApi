package com.sportapi.controllers;

import com.sportapi.model.ScoreCard;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/updateScore")
    @SendTo("/topic/scoreUpdates")
    public ScoreCard updateScore(ScoreCard updatedScoreCard) {
        // Process the updated score and broadcast it to subscribers
        return updatedScoreCard;
    }
}


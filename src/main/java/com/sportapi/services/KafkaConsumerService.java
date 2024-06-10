package com.sportapi.services;

import com.sportapi.model.ScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "score-updates", groupId = "scorecard-group")
    public void consumeScoreUpdate(ScoreCard scoreCard) {
        messagingTemplate.convertAndSend("/topic/score-updates", scoreCard);
    }
}

package com.example.kafka.controller;

import com.example.kafka.dto.MessageDto;
import com.example.kafka.utils.KafkaConstants;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody MessageDto messageDto) {
        messageDto.setTime(LocalDateTime.now());
        kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, messageDto);
    }
}

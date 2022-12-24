package com.example.kafka.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MessageDto {

    private String sender;
    private String content;
    private LocalDateTime time;
}

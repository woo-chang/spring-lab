package com.example.schedule.config;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
public class SchedulerConfiguration {

  @Scheduled(fixedDelay = 100000, zone = "Asia/Seoul")
  public void autoPrint() {
    LocalDateTime now = LocalDateTime.now();
    log.info(now + " : Test Scheduled!");
  }

}

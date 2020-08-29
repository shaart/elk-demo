package com.github.shaart.demo.elk.time;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TimeService {

  public TimeDto getTimeNow() {
    final LocalDateTime now = LocalDateTime.now();
    log.info("Current time is {}", now);
    final TimeDto timeDto = TimeDto.builder()
        .dateTime(now)
        .build();
    return timeDto;
  }
}

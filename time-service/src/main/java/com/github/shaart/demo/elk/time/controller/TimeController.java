package com.github.shaart.demo.elk.time.controller;

import com.github.shaart.demo.elk.time.service.TimeService;
import com.github.shaart.demo.elk.time.dto.v1.TimeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/time")
@RequiredArgsConstructor
public class TimeController {

  private final TimeService timeService;

  @GetMapping("/current")
  public TimeDto getTimeNow() {
    log.info("Request for a current time");
    final TimeDto timeNow = timeService.getTimeNow();
    log.info("Response for a current time is: {}", timeNow);
    return timeNow;
  }
}

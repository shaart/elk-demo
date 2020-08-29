package com.github.shaart.elk.task.service;

import com.github.shaart.elk.task.config.TaskProperties;
import com.github.shaart.elk.task.config.TaskProperties.TimeServer;
import com.github.shaart.elk.task.dto.v1.TaskDto;
import com.github.shaart.elk.task.dto.v1.TimeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

  private final RestTemplate restTemplate;
  private final TaskProperties properties;

  public TaskDto getCurrentTask() {
    String timeServiceUrl = getTimeServiceUrl();
    log.trace("Time service url: {}", timeServiceUrl);

    final String timeServiceCurrentTimeUrl = timeServiceUrl + "/time/current";
    log.debug("Sending a request for a current time to: {}", timeServiceCurrentTimeUrl);
    ResponseEntity<TimeDto> response = restTemplate
        .getForEntity(timeServiceCurrentTimeUrl, TimeDto.class);

    log.debug("Got a response for a current time time service");
    log.trace("Response is: {}", response);

    if (response.getBody() == null) {
      return TaskDto.builder()
          .message("unknown time, can't check tasks")
          .build();
    }

    return TaskDto.builder()
        .message("No tasks available for time " + response.getBody().getDateTime())
        .build();
  }

  private String getTimeServiceUrl() {
    final TimeServer timeService = properties.getTimeService();

    return timeService.getFullUrl();
  }
}

package com.github.shaart.elk.task.controller;

import com.github.shaart.elk.task.dto.v1.TaskDto;
import com.github.shaart.elk.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping("/current")
  public TaskDto getCurrentTasks() {
    log.info("Got a request for a task");

    final TaskDto currentTask = taskService.getCurrentTask();

    log.info("Response for a task is: {}", currentTask);
    return currentTask;
  }

  @GetMapping
  public TaskDto getTasks() {
    log.info("Got a request for tasks");

    final TaskDto currentTask = TaskDto.builder()
        .message("Tasks are unavailable")
        .build();

    log.info("Response for a tasks is: {}", currentTask);
    return currentTask;
  }
}

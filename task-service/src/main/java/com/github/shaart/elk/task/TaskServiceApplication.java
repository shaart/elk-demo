package com.github.shaart.elk.task;

import com.github.shaart.elk.task.config.TaskProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(TaskProperties.class)
@ComponentScan(basePackages = {
    "com.github.shaart.elk.task.config",
    "com.github.shaart.elk.task.controller",
    "com.github.shaart.elk.task.service"
})
public class TaskServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskServiceApplication.class, args);
  }

}

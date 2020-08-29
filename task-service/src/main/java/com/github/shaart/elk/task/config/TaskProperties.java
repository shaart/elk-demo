package com.github.shaart.elk.task.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "shaart.elk")
public class TaskProperties {

  private TimeServer timeService;

  @Data
  public static class TimeServer {

    private String host;
    private int port;

    public String getFullUrl() {
      final String host = getHost();
      final int port = getPort();

      return host + ":" + port;
    }
  }
}

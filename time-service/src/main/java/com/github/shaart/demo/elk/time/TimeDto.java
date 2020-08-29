package com.github.shaart.demo.elk.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TimeDto {

  @JsonProperty("date_time")
  private LocalDateTime dateTime;
}

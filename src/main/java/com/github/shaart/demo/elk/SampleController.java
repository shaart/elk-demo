package com.github.shaart.demo.elk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  @GetMapping("/")
  public String ping() {
    return "Pong";
  }
}

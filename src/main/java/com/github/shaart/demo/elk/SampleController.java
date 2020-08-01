package com.github.shaart.demo.elk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Slf4j
@RestController
public class SampleController {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${server.port}")
  private int port;

  @GetMapping("/ping")
  public String ping() {
    return "Pong";
  }

  @GetMapping("/hi")
  public String hi(@RequestParam(value = "user", required = false) String user) {
    String username = user == null ? "user" : user;
    final String response = "Hello, " + username;

    log.info("/hi - {}", response);

    return response;
  }

  @GetMapping("/sayhi")
  public String sayHi(@RequestParam(value = "user", required = false) String user) {
    final HashMap<String, String> uriVariables = new HashMap<>();
    if (user != null) {
      uriVariables.put("user", user);
    }

    log.info("/sayhi request to /hi with parameters: {}", uriVariables);
    final ResponseEntity<String> responseEntity = restTemplate
        .exchange("http://localhost:" + port + "/hi", HttpMethod.GET, null, String.class,
            uriVariables);

    final String body = responseEntity.getBody();
    log.info("/sayhi got response: {}", body);

    return body;
  }
}

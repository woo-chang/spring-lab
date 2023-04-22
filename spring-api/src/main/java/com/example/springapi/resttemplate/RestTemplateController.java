package com.example.springapi.resttemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/rest-template")
@RestController
public class RestTemplateController {

    private final ApplicationContext applicationContext;

    public RestTemplateController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/hello")
    public ResponseEntity<HelloResponse> hello() {
        final RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);
        final String url = "http://localhost:8081/hello";
        return restTemplate.getForEntity(url, HelloResponse.class);
    }
}

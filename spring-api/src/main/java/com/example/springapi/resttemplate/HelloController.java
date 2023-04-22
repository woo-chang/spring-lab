package com.example.springapi.resttemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<HelloResponse> hello() {
        return ResponseEntity.ok(new HelloResponse("hello", 200));
    }
}

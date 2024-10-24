package com.prac.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @GetMapping("/home")
    public ResponseEntity<Map<String, String>> handleHome() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the home page");
        return ResponseEntity.ok(response);
    }
}
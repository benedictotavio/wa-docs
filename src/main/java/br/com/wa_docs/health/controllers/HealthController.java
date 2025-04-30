package br.com.wa_docs.health.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/health")
public class HealthController {

    @GetMapping
    public String health() {
        System.out.println("Health check");
        try {
            return "OK";
        } catch (Exception e) {
            return "ERROR";
        }
    }
}
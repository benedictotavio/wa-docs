package br.com.wa_docs.user.auth.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaded_package.com.nimbusds.jose.shaded.gson.JsonObject;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping
    public JsonObject health() {
        JsonObject objectReturn = new JsonObject();
        objectReturn.addProperty("status", "ok");
        return objectReturn;
    }
}

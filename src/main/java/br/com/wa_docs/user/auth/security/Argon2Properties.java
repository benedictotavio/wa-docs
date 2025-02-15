package br.com.wa_docs.user.auth.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "argon2")
public class Argon2Properties {
    private int saltLength;
    private int hashLength;
    private int parallelism;
    private int memory;
    private int iterations;
}
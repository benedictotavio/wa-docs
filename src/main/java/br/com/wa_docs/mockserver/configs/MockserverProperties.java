package br.com.wa_docs.mockserver.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mockserver")
public class MockserverProperties {
    private String host;
    private Integer port;
}

package br.com.wa_docs.mockserver.configs;

import org.mockserver.integration.ClientAndServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockServerConfig {

    private ClientAndServer mockServer;

    @Bean
    ClientAndServer startMockServer() {
        mockServer = ClientAndServer.startClientAndServer(1080);
        return mockServer;
    }

    @Bean
    ClientAndServer stopMockServer() {
        return mockServer;
    }
}

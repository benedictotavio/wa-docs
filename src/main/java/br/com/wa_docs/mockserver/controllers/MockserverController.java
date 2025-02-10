package br.com.wa_docs.mockserver.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.mockserver.domains.Mockserver;
import br.com.wa_docs.mockserver.dtos.CreateMockserverDto;
import br.com.wa_docs.mockserver.dtos.MockserverDefaultResponseDto;
import br.com.wa_docs.mockserver.mappers.MockserverMappers;
import br.com.wa_docs.mockserver.services.IMockserverService;

@RestController
@RequestMapping("api/v1/mockserver")
public class MockserverController {

    private final IMockserverService mockserverService;
    private final MockserverMappers mockserverMappers;

    public MockserverController(IMockserverService mockserverService, MockserverMappers mockserverMappers) {
        this.mockserverService = mockserverService;
        this.mockserverMappers = mockserverMappers;
    }

    @PostMapping
    public ResponseEntity<MockserverDefaultResponseDto> create(@RequestBody CreateMockserverDto mockserverRequestDto) {
        try {
            Mockserver mockserver = this.mockserverMappers.toMockserver(mockserverRequestDto);
            Mockserver newMockserver = this.mockserverService.create(mockserver);
            return ResponseEntity.created(
                    URI.create("/api/v1/mockserver/")).body(
                            new MockserverDefaultResponseDto(
                                    newMockserver.getId(),
                                    "Mockserver created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

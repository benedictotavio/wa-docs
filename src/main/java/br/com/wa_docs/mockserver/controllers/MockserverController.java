package br.com.wa_docs.mockserver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.mockserver.domains.Mockserver;
import br.com.wa_docs.mockserver.dtos.CreateMockserverDto;
import br.com.wa_docs.mockserver.dtos.MockserverDefaultResponseDto;
import br.com.wa_docs.mockserver.dtos.MockserverResponseDto;
import br.com.wa_docs.mockserver.mappers.MockserverMappers;
import br.com.wa_docs.mockserver.services.IMockserverService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/v1/mockserver")
public class MockserverController {

    private final IMockserverService mockserverService;
    private final MockserverMappers mockserverMappers;

    public MockserverController(IMockserverService mockserverService, MockserverMappers mockserverMappers) {
        this.mockserverService = mockserverService;
        this.mockserverMappers = mockserverMappers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
        @PathVariable Long id,
        @RequestParam(required = false) Boolean asRequest
        ) {
        try {
            if (Boolean.TRUE.equals(asRequest)) {
                Mockserver mockserver = this.mockserverService.findById(id);
                return ResponseEntity.ok(
                    mockserverMappers.toRequest(mockserver)
                );
            }
            Mockserver mockserver = this.mockserverService.findById(id);
            return ResponseEntity.ok(
                mockserverMappers.toMockserverResponse(mockserver)
            );
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
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

    @GetMapping
    public ResponseEntity<List<MockserverResponseDto>> findByProjectId(
            @RequestParam(name = "project", required = false) Long projectId) {
        try {
            List<Mockserver> mockservers = this.mockserverService.findByProjectId(projectId);
            return ResponseEntity.ok(
                    mockservers.stream().map(
                            this.mockserverMappers::toMockserverResponse).toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

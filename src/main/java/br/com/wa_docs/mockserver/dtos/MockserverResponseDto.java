package br.com.wa_docs.mockserver.dtos;

import com.fasterxml.jackson.databind.util.JSONPObject;

public record MockserverResponseDto(
        String id,
        String name,
        String uri,
        String method,
        String body,
        String headers,
        String folderId,
        JSONPObject response) {
}
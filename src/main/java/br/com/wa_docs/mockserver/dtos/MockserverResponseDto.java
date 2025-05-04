package br.com.wa_docs.mockserver.dtos;

public record MockserverResponseDto(
        Long id,
        String name,
        String path,
        String baseUrl,
        String method,
        String body,
        String headers,
        String bodyResponse
        ) {
}
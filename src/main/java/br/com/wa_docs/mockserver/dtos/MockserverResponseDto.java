package br.com.wa_docs.mockserver.dtos;

public record MockserverResponseDto(
        Long id,
        String name,
        String url,
        String method,
        String body,
        String headers) {
}
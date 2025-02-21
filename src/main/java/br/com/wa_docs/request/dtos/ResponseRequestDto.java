package br.com.wa_docs.request.dtos;

public record ResponseRequestDto(
                Long id,
                String name,
                String uri,
                String method,
                String body,
                String headers) {
}

package br.com.wa_docs.request.dtos;

import br.com.wa_docs.request.enums.HttpMethod;

public record CreateRequestDto(
        String name,
        String uri,
        HttpMethod method,
        String body,
        String headers,
        String response,
        Long projectId) {
}
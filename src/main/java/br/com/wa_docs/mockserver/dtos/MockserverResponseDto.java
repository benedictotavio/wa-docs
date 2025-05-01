package br.com.wa_docs.mockserver.dtos;

import br.com.wa_docs.mockserver.enums.BodyFormat;

public record MockserverResponseDto(
        Long id,
        String name,
        String url,
        String method,
        String body,
        BodyFormat bodyFormat,
        String headers) {
}
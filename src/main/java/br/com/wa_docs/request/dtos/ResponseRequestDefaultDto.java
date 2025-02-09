package br.com.wa_docs.request.dtos;

public record ResponseRequestDefaultDto(
        String message,
        String status,
        Long timestamp) {

            public ResponseRequestDefaultDto(String message, String status) {
                this(message, status, System.currentTimeMillis());
            }
}

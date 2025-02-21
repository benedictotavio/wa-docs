package br.com.wa_docs.request.dtos;

public record ResponseRequestDefaultDto(
        Long id,
        String message,
        String status,
        Long timestamp) {

            public ResponseRequestDefaultDto(Long id,String message, String status) {
                this(id, message, status, System.currentTimeMillis());
            }

            public ResponseRequestDefaultDto(String message, String status) {
                this(null, message, status, System.currentTimeMillis());
            }
}

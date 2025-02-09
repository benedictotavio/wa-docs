package br.com.wa_docs.user.dtos;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String role) {
}

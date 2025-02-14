package br.com.wa_docs.user.dtos;

import java.util.Set;

import br.com.wa_docs.team.dtos.ResponseTeamDto;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String role,
        Set<ResponseTeamDto> teams
        ) {
}

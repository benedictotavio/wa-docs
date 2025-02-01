package br.com.wa_docs.team.dtos;

public record CreateTeamDto(
        String name,
        Long ownerId) {
}

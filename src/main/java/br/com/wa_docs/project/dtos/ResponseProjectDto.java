package br.com.wa_docs.project.dtos;

import br.com.wa_docs.team.dtos.ResponseTeamDto;

public record ResponseProjectDto(
                Long id,
                String name,
                String description,
                ResponseTeamDto team) {
}

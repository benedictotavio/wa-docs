package br.com.wa_docs.project.dtos;

import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.user.domains.User;

public record ResponseProjectDto(
        Long id,
        String name,
        String description,
        Team team,
        User owner) {
}

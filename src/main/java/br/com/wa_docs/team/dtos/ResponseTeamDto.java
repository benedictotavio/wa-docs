package br.com.wa_docs.team.dtos;

import java.time.LocalDate;

public record ResponseTeamDto(
        Long id,
        String name,
        LocalDate createdAt) {
}

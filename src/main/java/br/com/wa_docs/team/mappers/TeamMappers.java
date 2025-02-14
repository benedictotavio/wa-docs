package br.com.wa_docs.team.mappers;

import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.team.dtos.ResponseTeamDto;

public class TeamMappers {
    private TeamMappers() {}

    public static ResponseTeamDto toResponseTeamDto(Team team) {
        return new ResponseTeamDto(
            team.getTeamId(),
            team.getName(),
            team.getDescription(),
            team.getCreatedAt()
        );
    }
}

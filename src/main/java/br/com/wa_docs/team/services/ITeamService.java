package br.com.wa_docs.team.services;

import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.team.dtos.CreateTeamDto;

public interface ITeamService {
    Team getTeamById(Long id);

    Team createTeam(CreateTeamDto createTeamDto);

    Team updateTeam(Long id, Team team);

    void deleteTeam(Long id);
}

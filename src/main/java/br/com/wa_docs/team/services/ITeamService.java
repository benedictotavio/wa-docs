package br.com.wa_docs.team.services;

import br.com.wa_docs.team.domains.Team;

public interface ITeamService {
    Team getTeamById(Long id);
    Team createTeam(Team team);
    Team updateTeam(Long id, Team team);
    void deleteTeam(Long id);
}

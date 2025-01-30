package br.com.wa_docs.team.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.team.exceptions.TeamNotFoundException;
import br.com.wa_docs.team.repositories.TeamRepository;

@Service
public class TeamService implements ITeamService {

    private final TeamRepository teamRepository;

    TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team getTeamById(Long id) {
        Optional<Team> team = this.teamRepository.findById(id);

        if (team.isPresent()) {
            return team.get();
        } else {
            throw new TeamNotFoundException(
                String.format("Equipe com o id %d não encontrado.", id)
            );
        }
    }

    @Override
    public Team createTeam(Team team) {
        return this.teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Long id, Team team) {
        Team existingTeam = this.getTeamById(id);
        existingTeam.setName(team.getName());
        existingTeam.setOwner(team.getOwner());
        return this.teamRepository.save(existingTeam);
    }

    @Override
    public void deleteTeam(Long id) {
        this.teamRepository.deleteById(id);
    }

}

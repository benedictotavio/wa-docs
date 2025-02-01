package br.com.wa_docs.team.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.team.dtos.CreateTeamDto;
import br.com.wa_docs.team.exceptions.TeamNotFoundException;
import br.com.wa_docs.team.repositories.TeamRepository;
import br.com.wa_docs.user.services.IUserService;

@Service
public class TeamService implements ITeamService {

    private final TeamRepository teamRepository;
    private final IUserService userService;

    TeamService(TeamRepository teamRepository, IUserService userService) {
        this.teamRepository = teamRepository;
        this.userService = userService;
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
    public Team createTeam(CreateTeamDto createTeamDto) {
         Team team = new Team( 
            createTeamDto.name(),
            this.userService.getUserById(createTeamDto.ownerId())
         );
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

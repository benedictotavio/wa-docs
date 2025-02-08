package br.com.wa_docs.project.mappers;

import br.com.wa_docs.project.domain.Project;
import br.com.wa_docs.project.dtos.CreateProjectDto;
import br.com.wa_docs.project.dtos.ResponseProjectDto;
import br.com.wa_docs.team.domains.Team;
import br.com.wa_docs.team.dtos.ResponseTeamDto;
import br.com.wa_docs.user.domains.User;

public class ProjectMapper {

    private ProjectMapper() {
    }

    public static Project toProject(CreateProjectDto createProject, Team team, User owner) {
        return new Project(
                createProject.name(),
                createProject.description(),
                team,
                owner);
    }

    public static ResponseProjectDto toResponseProjectDto(Project project) {
        return new ResponseProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                new ResponseTeamDto(
                        project.getTeam().getTeamId(),
                        project.getTeam().getName(),
                        project.getTeam().getCreatedAt()));
    }
}
